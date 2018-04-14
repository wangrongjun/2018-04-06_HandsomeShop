package com.handsome.shop.test;

import com.handsome.shop.bean.*;
import com.handsome.shop.dao.*;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.java_util.LogUtil;
import com.wangrj.java_lib.java_util.MathUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dataSource.xml")
@ActiveProfiles("development")
public class DaoQueryUpdateDeleteTest {

    @Autowired
    private GoodsTypeDao goodsTypeDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsImageDao goodsImageDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private EvaluateDao evaluateDao;
    @Autowired
    private AddressDao addressDao;

    @After
    public void destroy() {
        HibernateDao.closeSession();
    }

    @Test
    public void testQuery() {

        List<Orders> ordersList = ordersDao.queryByCustomerId(customerDao.queryByPhone("15521302230").getCustomerId());
        for (Orders orders : ordersList) {
            LogUtil.print(orders);
            LogUtil.print(orders.getGoods());
            LogUtil.print(orders.getGoods().getGoodsImageList());
            LogUtil.print("-------------------------------");
        }

        LogUtil.printEntity(customerDao.queryByPhone("15521302230"));
        LogUtil.printEntity(customerDao.countGender());

        List<Shop> shopList = shopDao.queryAll();
        for (Shop shop : shopList) {
            LogUtil.print(shop);
            LogUtil.print(shop.getSeller());
        }

        List<GoodsType> goodsTypeList = goodsTypeDao.queryAll();
        for (GoodsType goodsType : goodsTypeList) {
            LogUtil.print(goodsType);
        }

        LogUtil.printEntity(shopCarDao.queryCountByCustomerId(25));

        System.out.println("******************************");
        Goods 菜心 = goodsDao.queryBySearchWord("菜心", 0, 1).get(0);
        List<GoodsImage> goodsImageList = 菜心.getGoodsImageList();
//        List<GoodsImage> goodsImageList = goodsImageDao.queryByGoodsId(菜心);
        for (GoodsImage goodsImage : goodsImageList) {
            LogUtil.print(goodsImage);
        }

//        LogUtil.printEntity(goodsDao.queryCountByShopId(13));
//        LogUtil.printEntity(goodsDao.queryByShopId(13, 0, 0), "goodsType", "shop", "goodsImageList");
//        LogUtil.printEntity(goodsDao.queryCountByCustomerId(25));
//        LogUtil.printEntity(goodsDao.queryByCustomerId(25, 0, 0), "goodsType", "shop", "goodsImageList");
//        LogUtil.printEntity(goodsDao.queryCountByGoodsTypeId(1));
//        LogUtil.printEntity(goodsDao.queryByGoodsTypeId(1, 0, 0), "goodsType", "shop", "goodsImageList");
//        LogUtil.printEntity(goodsDao.queryCountBySearchWord("3"));
//        LogUtil.printEntity(goodsDao.queryBySearchWord("3", 0, 0), "goodsType", "shop", "goodsImageList");

//        LogUtil.printEntity(ordersDao.queryCountByCustomerId(25));
//        LogUtil.printEntity(ordersDao.queryByCustomerId(25), "customer", "goodsType", "shop", "goodsImageList");
//        LogUtil.printEntity(ordersDao.queryCountByGoodsId(17));

//        LogUtil.printEntity(addressDao.queryByCustomerId(25), "customer");

//        LogUtil.printEntity(evaluateDao.queryByGoodsId(17));
    }

    @Test
    public void testUpdate() {
        String s = "newHeadUrl: " + MathUtil.random(0, 100);
        LogUtil.print(s);
        customerDao.updateHeadUrl(31, s);
        Customer customer = customerDao.queryById(31);
        LogUtil.print(customer.getHeadUrl());
    }

}
