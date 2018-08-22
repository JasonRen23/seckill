package cn.jasonren.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀的商品
 *
 * @author : JasonRen
 * @date : 2018/06/30
 * @email : zhicheng_ren@163.com
 */
public class Seckill implements Serializable {

    private static final long serialVersionUID = 2912164127598660137L;
    /**
     * 主键ID
     */
    private long seckillId;
    /**
     * 秒杀商品名字
     */
    private String name;
    /**
     * 商品数量
     */
    private int number;
    /**
     * 开始秒杀时间
     */
    private Date startTime;
    /**
     * 结束秒杀时间
     */
    private Date endTime;
    /**
     * 创建的时间
     */
    private Date createTime;

    public Seckill() {
    }

    public Seckill(long seckillId, String name, int number, Date startTime, Date endTime, Date createTime) {
        this.seckillId = seckillId;
        this.name = name;
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "cn.jasonren.seckill.entity.Seckill{" +
            "主键ID=" + seckillId +
            ", 秒杀商品='" + name + '\'' +
            ", 商品数量=" + number +
            ", 开始秒杀时间=" + startTime +
            ", 结束秒杀时间=" + endTime +
            ", 创建时间=" + createTime +
            "}\n";
    }
}
