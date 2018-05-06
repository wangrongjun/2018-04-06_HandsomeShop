package com.handsome.shop.entity.view;

import com.handsome.shop.util.Pager;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * by wangrongjun on 2018/5/5.
 */
public class PageParam {

    @Min(value = 0, message = "pageIndex must unless than zero")
    private int pageIndex;// 从0开始
    @Min(value = 0, message = "pageSize must unless than zero")
    private int pageSize;// 如果等于0，代表不限制数量，返回全部数据
    @Pattern(regexp = "-?[a-zA-Z0-9|_]+(,-?[a-zA-Z0-9|_]+)*", message = "sortBy pattern is error")
    private String sortBy;// 排序的字段名，前面加-号代表倒序，否则正序。每个字段以逗号分隔。

    public <T> Pager<T> toPager(Class<T> entityClass) {
        if (sortBy == null) {
            return new Pager<>(pageIndex, pageSize, null);
        }
        String[] sortByList;
        if (sortBy.contains(",")) {
            sortByList = sortBy.split(",");
        } else {
            sortByList = new String[]{sortBy};
        }
        return new Pager<>(pageIndex, pageSize, sortByList);
    }

    public PageParam() {
    }

    public PageParam(String sortBy) {
        this.sortBy = sortBy;
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

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
