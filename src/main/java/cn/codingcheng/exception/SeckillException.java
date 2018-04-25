package cn.codingcheng.exception;
/**
 * @author : JasonRen
 * @date : 2018/4/25
 * @email : zhicheng_ren@163.com
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message){
        super(message);
    }

    public SeckillException(String message, Throwable cause){
        super(message, cause);
    }
}
