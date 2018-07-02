package cn.jasonren.seckill.enums;

/**
 * @author : JasonRen
 * @date : 2018/07/02
 * @email : zhicheng_ren@163.com
 */
public enum SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KRILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;
    private String info;

    SeckillStatEnum(){

    }

    SeckillStatEnum(int state, String info){

    }

    public int getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

    public static SeckillStatEnum stateOf(int index){
        for(SeckillStatEnum statEnum : values()){
            if(statEnum.getState() == index){
                return statEnum;
            }
        }
        return null;
    }
}
