package com.sdut.onlinestore.vo;

public class PageVo {
    private Integer start  =1 ;
    private Integer rows = 10 ;


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
