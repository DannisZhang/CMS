package org.dannis.cms.result;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-05-07 10:17
 */
public class BaseResult {
    private int code;
    private boolean success;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
