package cn.codingcheng.dto;



import cn.codingcheng.entity.SuccessKilled;
import cn.codingcheng.enums.SeckillStateEnum;

/**
 * @author : JasonRen
 * @date : 2018/4/25
 * @email : zhicheng_ren@163.com
 */
public class SeckillExecution {
    private long seckillId;


    /**
    * @Fields state :  秒杀执行结果的状态
    */
    private int state;


    /**
    * @Fields stateInfo : 状态的明文标识
    */
    private String stateInfo;


    /**
    * @Fields successKilled : 当秒杀成功时，需要传递秒杀成功的对象回去
    */
    private SuccessKilled successKilled;


    /**
     * 秒杀成功返回所有信息
     * @param seckillId
     * @param stateNum
     * @param successKilled
     * @return
     */

    public SeckillExecution(long seckillId, SeckillStateEnum stateNum, SuccessKilled successKilled){
        this.seckillId = seckillId;
        this.state = stateNum.getState();
        this.stateInfo = stateNum.getInfo();
        this.successKilled = successKilled;
    }


    /**
     * 秒杀失败
     * @param seckillId
     * @param stateEnum
     * @return
     */
    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum){
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
    }


    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString(){
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo=" + stateInfo + "\'" +
                ", successKilled=" + successKilled +
                '}';
    }
}
