package com.esc.fms.entity;

/**
 * Created by tangjie on 2017/1/17.
 */

import java.util.List;

public class PageModel {

    private Integer total = 0;  // 总共数量
    private Integer limit = 10;  // 每页数量
    private Integer pageNo = 1; // 当前分页数
    private Integer pageTotal = 0; //分页总数
    private List<?> rows; // 当前页数据
    private Integer startIndex = 0;  // 当前页起始记录
    private Integer endIndex = 0;  // 当前页结束记录
    private Integer orderId = 0;  // order 条件
    private Integer groupById = 0;  // group by 条件

    public void pageModelInfo(){
        if(total==0){
            pageNo = 0;
        }
        setPageNo(pageNo);
        // 总页数=(总记录数+每页行数-1)/每页行数
        pageTotal = total / limit;
        int remainde = total % limit;
        if(remainde > 0){
            pageTotal += 1;
        }
        setPageTotal(pageTotal);
        if(pageNo >0){
            setStartIndex((pageNo - 1)*limit);
            setEndIndex(pageNo*limit);
        }
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        pageModelInfo();
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGroupById() {
        return groupById;
    }

    public void setGroupById(Integer groupById) {
        this.groupById = groupById;
    }


}
