package com.mav.controller;

public class AJAXresultData {


    private Boolean success = Boolean.FALSE;
    private String status;
    private Object page;
    private Object pageCount;
    private Object total;
    private Object rows;

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public Object getPageCount() {
        return pageCount;
    }

    public void setPageCount(Object pageCount) {
        this.pageCount = pageCount;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
