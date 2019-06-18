package com.qf.manager.pojo.dto;

/**
 * 封装入参
 */
public class PageParam {

    private long page;
    private long limit;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getOffset() {
        return (page-1)*limit;
    }

}
