package cn.jasonren.seckill.dao;

import cn.jasonren.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;

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
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone, @Param("createTime") Date createTime);

    /**
     * 根据秒杀商品的ID查询<code>SuccessKilled</code>的明细信息
     *
     * @param seckillId 秒杀商品的ID
     * @param userPhone 购买用户的手机号码
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
