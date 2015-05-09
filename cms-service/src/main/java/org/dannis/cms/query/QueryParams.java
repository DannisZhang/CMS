package org.dannis.cms.query;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-09 13:18
 */
public class QueryParams {
    /**
     * default page number constant
     */
    public static final int DEFAULT_PAGE_NUMBER = 1;
    /**
     * default page size constant
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * page number
     */
    private int page = DEFAULT_PAGE_NUMBER;
    /**
     * page size
     */
    private int rows = DEFAULT_PAGE_SIZE;
    /**
     * parameters
     */
    private Map<String, Object> params;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put("offset", getOffset());
        params.put("limit", getLimit());
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        if (params != null) {
            Object obj = params.get(key);
            if (obj != null) {
                return (String) obj;
            }
        }

        return defaultValue;
    }

    public int getInt(String key) {
        return getInt(key, -1);
    }


    public int getInt(String key, int defaultValue) {
        if (params != null) {
            Object obj = params.get(key);
            if (obj != null && !"".equals(((String) obj).trim())) {
                return Integer.valueOf((String) obj);
            }
        }

        return defaultValue;
    }

    /**
     * Get the row number in database table where query start
     *
     * @return start query page number
     */
    public int getOffset() {
        if (page < 1) {
            page = DEFAULT_PAGE_NUMBER;
        }
        return (page - 1) * rows;
    }

    /**
     * Get the limitation number for query
     *
     * @return page size
     */
    public int getLimit() {
        if (rows < 1) {
            rows = DEFAULT_PAGE_SIZE;
        }
        return rows;
    }
}
