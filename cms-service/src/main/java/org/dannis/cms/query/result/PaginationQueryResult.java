package org.dannis.cms.query.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询结果类
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-09 13:33
 */
public class PaginationQueryResult<T> extends BaseResult {
    /**
     * 总数
     */
    private long total;
    /**
     * 数据集合
     */
    private List<T> rows;

    public PaginationQueryResult() {
        this(0l, new ArrayList<T>());
    }

    public PaginationQueryResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows == null ? new ArrayList<T>() : rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
