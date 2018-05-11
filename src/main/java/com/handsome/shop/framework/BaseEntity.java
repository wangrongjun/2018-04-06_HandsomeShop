package com.handsome.shop.framework;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * by wangrongjun on 2018/4/14.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    public static final String OBSOLETE_DATE_IS_NULL = "obsoleteDate IS NULL";

    private Date createdOn = new Date();
    private Date obsoleteDate;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getObsoleteDate() {
        return obsoleteDate;
    }

    public void setObsoleteDate(Date obsoleteDate) {
        this.obsoleteDate = obsoleteDate;
    }
}
