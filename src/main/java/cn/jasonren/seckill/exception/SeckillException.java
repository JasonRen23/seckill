package cn.jasonren.seckill.exception;

/**
 * 秒杀基础的异常
 *
 * @author : JasonRen
 * @date : 2018/07/02
 * @email : zhicheng_ren@163.com
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
