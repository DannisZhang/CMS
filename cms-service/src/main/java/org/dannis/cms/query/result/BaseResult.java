package org.dannis.cms.query.result;

/**
 * 基本结果类
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-09 13:23
 */
public class BaseResult {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 消息
     */
    private String message;

    public BaseResult(){
        this(true,"");
    }

    public BaseResult(boolean success,String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
