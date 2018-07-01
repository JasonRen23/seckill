package cn.jasonren.cheetahSeckill.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 秒杀后的状态
 * @author : JasonRen
 * @date : 2018/06/30
 * @email : zhicheng_ren@163.com
 * @since :1.8
 */
public class SuccessKilled implements Serializable{
    private static final Long serialVersionUID = 1834437127882846202L;

    private long seckillId;
    /**
     * 用户的手机号
     * */
    private long userPhone;
    /**
     * 秒杀状态
     * */
    private short state;
    /**
     * 创建时间
     * */
    private LocalDateTime createTime;
    /**
     * 多对一，因为一件商品在库存中肯定有许多，对应的购买信息也有很多
     * */
    private Seckill seckill;

    public SuccessKilled() {
    }

    public SuccessKilled(long seckillId, long userPhone, short state, LocalDateTime createTime, Seckill seckill) {
        this.seckillId = seckillId;
        this.userPhone = userPhone;
        this.state = state;
        this.createTime = createTime;
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "主键ID=" + seckillId +
                ", 手机号码=" + userPhone +
                ", 秒杀状态=" + state +
                ", 创建时间=" + createTime +
                ", 秒杀的商品=" + seckill +
                "}\n";
    }
}
