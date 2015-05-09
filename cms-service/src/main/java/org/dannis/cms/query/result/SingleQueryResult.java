package org.dannis.cms.query.result;

/**
 * 单查询结果类
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-09 13:29
 */
public class SingleQueryResult<T> extends BaseResult {
    /**
     * 数据
     */
    private T data;

    public SingleQueryResult() {
        //do nothing
    }
    public SingleQueryResult(T data) {
        this(true,"",data);
    }

    public SingleQueryResult(boolean success,String message,T data) {
        super(success,message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
