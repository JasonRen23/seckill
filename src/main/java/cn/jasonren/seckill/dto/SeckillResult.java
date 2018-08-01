package cn.jasonren.seckill.dto;

/**
 * @author : JasonRen
 * @date : 2018-07-30 上午1:04
 * @email : zhicheng_ren@163.com
 *
 * 将所有的ajax请求返回类型，全部封装json结果
 */
public class SeckillResult<T> {

    private boolean success;
    private T data;
    private String error;

    public SeckillResult(final boolean success, final T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(final boolean success, final String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }
}
