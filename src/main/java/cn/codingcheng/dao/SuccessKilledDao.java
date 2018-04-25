package cn.codingcheng.dao;

import cn.codingcheng.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;
/**
 * @author : JasonRen
 * @date : 2018/4/25
 * @email : zhicheng_ren@163.com
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细,可过滤重复
     * @param seckillId
     * @param userPhone
     * @return插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了Seckill秒杀产品对象)
     * @param seckillId
     * @param userPhone
     * @return
     */

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
