package cn.codingcheng.exception;
/**
 * @author : JasonRen
 * @date : 2018/4/25
 * @email : zhicheng_ren@163.com
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
