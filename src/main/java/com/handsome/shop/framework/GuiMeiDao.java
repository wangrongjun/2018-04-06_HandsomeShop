package com.handsome.shop.framework;

import com.wangrj.java_lib.db2.BaseDao;

/**
 * by wangrongjun on 2017/6/17.
 */
public abstract class GuiMeiDao<T> extends BaseDao<T> {

    public GuiMeiDao() {
        super(Config.username, Config.password, Config.dbName, Config.printSql, Config.printResult);
    }

}
