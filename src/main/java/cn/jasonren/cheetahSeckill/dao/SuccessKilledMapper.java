package cn.jasonren.cheetahSeckill.dao;

import cn.jasonren.cheetahSeckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @author : JasonRen
 * @date : 2018/07/01
 * @email : zhicheng_ren@163.com
 */
public interface SuccessKilledMapper {
    /**
     * 插入一条详细的购买信息
     *
     * @param seckillId 秒杀商品的
     * @param userPhone 购买用户的手机号码
     * @return 成功插入就返回1, 否则就返回0
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品的ID查询<code>SuccessKilled</code>的明细信息
     *
     * @param seckillId 秒杀商品的ID
     * @param userPhone 购买用户的手机号码
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
