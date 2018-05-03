package com.handsome.shop.util;

import java.util.List;

/**
 * by wangrongjun on 2017/9/27.
 */
public class Pager<T> {

    private List<T> dataList;// 注意，dataList只包含pageIndex当前页的数据，而非所有数据！
    private int pageIndex;// 从0开始
    private int pageSize;// 如果等于0，代表不限制数量，返回全部数据
    private int totalCount;// 查询返回的全部记录总数
    private String[] sortByList;// 排序的字段名，前面加-号代表倒序，否则正序

    public Pager() {
    }

    public Pager(Integer pageIndex, Integer pageSize, String[] sortByList) {
        this.pageIndex = pageIndex != null ? pageIndex : 0;
        this.pageSize = pageSize != null ? pageSize : 0;
        this.sortByList = sortByList;
    }

    public int getOffset() {
        return pageIndex * pageSize;
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

    public String[] getSortByList() {
        return sortByList;
    }

    public void setSortByList(String[] sortByList) {
        this.sortByList = sortByList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
