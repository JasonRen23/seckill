package cn.codingcheng.dao;

import cn.codingcheng.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
/**
 * @author : JasonRen
 * @date : 2018/4/25
 * @email : zhicheng_ren@163.com
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param  seckillId
     * @param  killTime
     * @return
     */

    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀的商品信息
     * @param seckillId
     * @return
     */

    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);


}
