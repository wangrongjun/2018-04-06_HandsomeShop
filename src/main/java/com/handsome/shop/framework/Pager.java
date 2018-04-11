package com.handsome.shop.framework;

import java.util.List;

/**
 * by wangrongjun on 2017/9/27.
 */
public class Pager<T> {

    private List<T> dataList;// 注意，dataList只包含pageIndex当前页的数据，而非所有数据！
    private int pageIndex;
    private int pageSize;
    private int rowCount;
    private int sortType;

    public Pager(List<T> dataList, int pageIndex, int pageSize, int rowCount, int sortType) {
        this.dataList = dataList;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
        this.sortType = sortType;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }
}
