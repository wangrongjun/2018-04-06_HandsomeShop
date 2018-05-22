package com.handsome.shop;

import com.handsome.shop.dao.*;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.util.GsonConverter;
import com.wangrj.java_lib.hibernate.Where;
import com.wangrj.java_lib.java_util.FileUtil;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * by wangrongjun on 2018/4/14.
 */
@ActiveProfiles("development")
public class DaoQueryUpdateDeleteTest extends BaseDaoTest {

    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Resource
    private GoodsTypeRelationDao goodsTypeRelationDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private SellerDao sellerDao;
    @Resource
    private ShopDao shopDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private OrdersDao ordersDao;
    @Resource
    private ShopCarDao shopCarDao;
    @Resource
    private EvaluateDao evaluateDao;
    @Resource
    private ContactDao contactDao;
    @Resource
    private PictureDao pictureDao;
    @Resource
    private RefundDao refundDao;
    @Resource
    private GoodsAttrNameDao goodsAttrNameDao;
    @Resource
    private GoodsAttrValueDao goodsAttrValueDao;

    @Test
    public void testQuery() throws IOException {

        Shop shop = shopDao.query(Where.eq("shopName", "东方电脑城")).get(0);
        String json = GsonConverter.toJson(shop, "Goods.goodsAttrNames", "Goods.shop", "Seller.shopList");
        System.out.println(json);
        FileUtil.write(json, "E:/Test/a.txt");

//        Goods goods = goodsDao.queryById(69);
//        System.out.println(goods);

//        List<Orders> ordersList = ordersDao.queryByCustomerId(customerDao.queryByPhone("15521302230").getCustomerId(), null);
//        for (Orders orders : ordersList) {
//            LogUtil.print(orders);
//            LogUtil.print(orders.getGoods());
//            LogUtil.print(orders.getGoods().getGoodsImageList());
//            LogUtil.print("-------------------------------");
//        }

//        LogUtil.printEntity(customerDao.queryByPhone("15521302230"));
//        LogUtil.printEntity(customerDao.countGender());

//        List<Shop> shopList = shopDao.queryAll();
//        for (Shop shop : shopList) {
//            LogUtil.print(shop);
//            LogUtil.print(shop.getSeller());
//        }

//        List<GoodsType> goodsTypeList = goodsTypeDao.queryAll();
//        for (GoodsType goodsType : goodsTypeList) {
//            LogUtil.print(goodsType);
//        }

//        LogUtil.printEntity(shopCarDao.queryCountByCustomerId(25));

//        System.out.println("******************************");
//        Goods 菜心 = goodsDao.queryBySearchWord("菜心", 0, 1).get(0);
//        List<GoodsImage> goodsImageList = 菜心.getGoodsImageList();
//        List<GoodsImage> goodsImageList = goodsImageDao.queryByGoodsId(菜心);
//        for (GoodsImage goodsImage : goodsImageList) {
//            LogUtil.print(goodsImage);
//        }

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

//        LogUtil.printEntity(contactDao.queryByCustomerId(25), "customer");

//        LogUtil.printEntity(evaluateDao.queryByGoodsId(17));
    }

//    @Test
//    public void testUpdate() {
//        String s = "newHeadUrl: " + MathUtil.random(0, 100);
//        LogUtil.print(s);
//        customerDao.updateHeadUrl(31, s);
//        Customer customer = customerDao.queryById(31);
//        LogUtil.print(customer.getHeadUrl());

//        ShopCar shopCar = new ShopCar(new Customer(25), new Goods(17));
//        shopCarDao.insert(shopCar);
//    }

}
