package cn.jasonren.seckill.service;


import cn.jasonren.seckill.dao.SeckillMapper;
import cn.jasonren.seckill.dto.Exposer;
import cn.jasonren.seckill.entity.Seckill;
import cn.jasonren.seckill.service.interfaces.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

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
    private SeckillMapper successKilledMapper;


    public List<Seckill> getSeckillList() {
        return seckillMapper.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillMapper.queryById(seckillId);
    }

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
}
