package com.handsome.shop.framework;

import com.handsome.shop.dao.*;
import com.handsome.shop.dao.impl.*;

/**
 * by wangrongjun on 2017/7/12.
 */
public class DaoFactory {

    public static CustomerDao getCustomerDao() {
        return new CustomerDaoImpl();
    }

    public static GoodsTypeDao getGoodsTypeDao() {
        return new GoodsTypeDaoImpl();
    }

    public static SellerDao getSellerDao() {
        return new SellerDaoImpl();
    }

    public static ShopDao getShopDao() {
        return new ShopDaoImpl();
    }

    public static GoodsDao getGoodsDao() {
        return new GoodsDaoImpl();
    }

    public static GoodsImageDao getGoodsImageDao() {
        return new GoodsImageDaoImpl();
    }

    public static OrdersDao getOrdersDao() {
        return new OrdersDaoImpl();
    }

    public static ShopCarDao getShopCarDao() {
        return new ShopCarDaoImpl();
    }

    public static EvaluateDao getEvaluateDao() {
        return new EvaluateDaoImpl();
    }

    public static AddressDao getAddressDao() {
        return new AddressDaoImpl();
    }

}
