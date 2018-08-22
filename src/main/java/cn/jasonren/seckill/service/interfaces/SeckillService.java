package cn.jasonren.seckill.service.interfaces;

import cn.jasonren.seckill.dto.SeckillExecution;
import cn.jasonren.seckill.entity.Seckill;
import cn.jasonren.seckill.dto.Exposer;
import cn.jasonren.seckill.exception.RepeatKillException;
import cn.jasonren.seckill.exception.SeckillCloseException;
import cn.jasonren.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return类型）
 *
 * @author : JasonRen
 * @date : 2018/07/02
 * @email : zhicheng_ren@163.com
 */
public interface SeckillService {
    /**
     * 查询全部的秒杀记录
     *
     * @return List<Seckill>
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return 根据ID查询出来的记录信息
     */
    Seckill getById(long seckillId);

    /**
     * 在秒杀开启时输出秒杀接口的地址，否则输出系统时间跟秒杀时间
     *
     * @param seckillId
     * @return 根据对应的状态返回对应的状态实体
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作，有可能是失败的，失败就抛异常
     *
     * @param seckillId 秒杀的商品ID
     * @param userPhone 手机号码
     * @param md5       md5加密值
     * @return 根据不同的结果返回不同的实体信息
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;

    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);

}
