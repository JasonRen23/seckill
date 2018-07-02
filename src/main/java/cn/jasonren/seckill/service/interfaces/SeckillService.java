package cn.jasonren.seckill.service.interfaces;

import cn.jasonren.seckill.entity.Seckill;
import cn.jasonren.seckill.dto.Exposer;

import java.util.List;

/**
 * @author : JasonRen
 * @date : 2018/07/02
 * @email : zhicheng_ren@163.com
 */
public interface SeckillService {
    /**
     * 查询全部的秒杀记录
     * @return List<Seckill>
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return 根据ID查询出来的记录信息
     */
    Seckill getById(long seckillId);

    /**
     * 在秒杀开启时输出秒杀接口的地址，否则输出系统时间跟秒杀地址
     *
     * @param seckillId
     * @return 根据对应的状态返回对应的状态实体
     */
    Exposer exportSeckillUrl(long seckillId);


}
