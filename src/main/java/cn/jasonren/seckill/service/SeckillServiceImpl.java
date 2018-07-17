package cn.jasonren.seckill.service;


import cn.jasonren.seckill.dao.SeckillMapper;
import cn.jasonren.seckill.dao.SuccessKilledMapper;
import cn.jasonren.seckill.dto.Exposer;
import cn.jasonren.seckill.dto.SeckillExecution;
import cn.jasonren.seckill.entity.Seckill;
import cn.jasonren.seckill.entity.SuccessKilled;
import cn.jasonren.seckill.enums.SeckillStatEnum;
import cn.jasonren.seckill.exception.RepeatKillException;
import cn.jasonren.seckill.exception.SeckillCloseException;
import cn.jasonren.seckill.exception.SeckillException;
import cn.jasonren.seckill.service.interfaces.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : JasonRen
 * @date : 2018/07/02
 * @email : zhicheng_ren@163.com
 */
@Service
public class SeckillServiceImpl implements SeckillService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 加入salt值，用于混淆
     */
    private final String salt = "thisIsSaltValue";

    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private SuccessKilledMapper successKilledMapper;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillMapper.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillMapper.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillMapper.queryById(seckillId);
        if(seckill == null){
            logger.warn("查询不到这个秒杀产品的记录");
            return new Exposer(false, seckillId);
        }

        //判断是否还没到秒杀时间或是过了秒杀时间
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        //开始时间大于现在的时候说明没有开始秒杀活动；秒杀活动结束时间小于现在的时间说明秒杀已经结束了
        if(nowTime.getTime() > startTime.getTime() && nowTime.getTime() < endTime.getTime()){
            //秒杀开启，返回秒杀商品的id，用给接口加密的md5
            String md5 = getMd5(seckillId);
            return new Exposer(true, md5, seckillId);
        }
        return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
    }

    private String getMd5(long seckillId){
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 执行秒杀操作，失败就抛出异常
     *
     * @param seckillId 秒杀的商品ID
     * @param userPhone 手机号码
     * @param md5       md5加密值
     * @return          根据不同的结果返回不同的实体信息
     */
    @Transactional
    @Override
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException, RepeatKillException, SeckillException {
        if(md5 == null || md5.equals(getMd5(seckillId))){
            logger.error("秒杀数据被篡改");
            throw new SeckillException("seckill data rewrite");
        }
        // 执行秒杀业务逻辑
        Date nowTime = new Date();

        try{
            int reduceNumber = seckillMapper.reduceNumber(seckillId, nowTime);
            if(reduceNumber <= 0){
                logger.warn("没有更新数据库记录，说明秒杀结束");
                throw new SeckillCloseException("seckill is closed");
            }else {
                int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
                if(insertCount <= 0){
                    throw new RepeatKillException("seckill repeated");
                }else {
                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException | RepeatKillException e1){
            throw e1;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            //把编译期一场转换为运行时异常
            throw new SeckillException("seckill inner error : " + e.getMessage());
        }
    }

    @Override
    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5){
        if(md5 == null || !md5.equals(getMd5(seckillId))){
            return new SeckillExecution(seckillId, SeckillStatEnum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        //执行储存过程，result被复制
        try {
            seckillMapper.killByProcedure(map);
            //获取result
            int result = MapUtils.getInteger(map, "result", -2);
            if(result == 1){
                SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
            }else {
                return new SeckillExecution(seckillId, SeckillStatEnum.stateOf(result));
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
        }

    }
}
