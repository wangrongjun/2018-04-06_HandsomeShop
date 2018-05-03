package com.handsome.shop;

import com.handsome.shop.entity.*;
import com.handsome.shop.dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * by wangrongjun on 2017/6/17.
 */
public class DaoInsertTest extends BaseDaoTest {

    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private SellerDao sellerDao;
    @Resource
    private ShopDao shopDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsImageDao goodsImageDao;
    @Resource
    private OrdersDao ordersDao;
    @Resource
    private ShopCarDao shopCarDao;
    @Resource
    private EvaluateDao evaluateDao;
    @Resource
    private AddressDao addressDao;

    /**
     * GoodsType  1-10
     * Seller     11-12
     * Shop       13-16
     * Goods      17-24
     * Customer   25-31
     * Orders     83-90
     * Evaluate   91-94
     * GoodsImage 95-112
     */
    @Test
    public void testInsert() {
        Session session = sessionFactory.openSession();

        GoodsType 电子产品 = new GoodsType("电子产品");
        GoodsType 食品 = new GoodsType("食品");
        goodsTypeDao.insert(电子产品);
        goodsTypeDao.insert(食品);
        goodsTypeDao.insert(new GoodsType("服饰"));
        goodsTypeDao.insert(new GoodsType("家电"));
        goodsTypeDao.insert(new GoodsType("玩具"));
        goodsTypeDao.insert(new GoodsType("运动"));
        goodsTypeDao.insert(new GoodsType("书籍"));
        goodsTypeDao.insert(new GoodsType("汽车"));
        goodsTypeDao.insert(new GoodsType("二手"));
        goodsTypeDao.insert(new GoodsType("其他"));

        Seller 张三 = new Seller("110", "123", "张三", "明月公主", 0, "/admin/img/seller_1.jpg");
        Seller 李四 = new Seller("120", "123", "李四", "型男", 1, "/admin/img/seller_2.jpg");
        Shop 东方电脑城 = new Shop(张三, "东方电脑城", "专卖电脑及其设备", "/admin/img/shop_1.jpg");
        Shop 手机旗舰店 = new Shop(张三, "手机旗舰店", "出售最新的三星，苹果手机", "/admin/img/shop_2.jpg");
        Shop 生鲜店 = new Shop(李四, "生鲜店", "各种新鲜水果，蔬菜应有尽有！", "/admin/img/shop_3.jpg");
        Shop 零食店 = new Shop(李四, "零食店", "吃货预备营！", "/admin/img/shop_4.jpg");

        Goods 宏基笔记本 = new Goods("宏基笔记本", "Aspire最新版", 20, 电子产品, 东方电脑城, 3200);
        Goods 苹果笔记本 = new Goods("苹果笔记本", "超薄迷你", 60, 电子产品, 东方电脑城, 5400);
        Goods 三星E7手机 = new Goods("三星E7手机", "E7系列", 38, 电子产品, 手机旗舰店, 1500);
        Goods iPhone7手机 = new Goods("iPhone7手机", "乔布斯呕心沥血之作！", 8, 电子产品, 手机旗舰店, 5000);
        Goods 菜心 = new Goods("菜心", "新鲜的菜心一斤，纯天然无农药", 200, 食品, 生鲜店, 7.5);
        Goods 苹果 = new Goods("苹果", "新鲜的苹果一斤，纯天然无农药", 80, 食品, 生鲜店, 11.2);
        Goods 辣条 = new Goods("辣条", "老外都抢着吃，欲购从速！", 250, 食品, 零食店, 2.5);
        Goods 可乐 = new Goods("可乐", "透心凉，心飞扬！", 300, 食品, 零食店, 5);

        sellerDao.insert(张三);
        sellerDao.insert(李四);
        shopDao.insert(东方电脑城);
        shopDao.insert(手机旗舰店);
        shopDao.insert(生鲜店);
        shopDao.insert(零食店);
        goodsDao.insert(宏基笔记本);
        goodsDao.insert(苹果笔记本);
        goodsDao.insert(三星E7手机);
        goodsDao.insert(iPhone7手机);
        goodsDao.insert(菜心);
        goodsDao.insert(苹果);
        goodsDao.insert(辣条);
        goodsDao.insert(可乐);

        Customer 王荣俊 = new Customer("15521302230", "123", "王荣俊", "英俊", 1, "/admin/img/customer_1.jpg");
        Customer 沫沫 = new Customer("13023796942", "123", "沫沫", "沫宝儿", 0, "/admin/img/customer_2.jpg");

        customerDao.insert(王荣俊);
        customerDao.insert(沫沫);
        for (int i = 1; i <= 50; i++) {
            Customer customer = new Customer("155" + i, "123", "user" + i,
                    "nick" + i, i % 4 == 0 ? 1 : 0, "img" + i);
            customerDao.insert(customer);
        }

        shopCarDao.insert(new ShopCar(王荣俊, iPhone7手机));
        shopCarDao.insert(new ShopCar(王荣俊, 苹果笔记本));
        shopCarDao.insert(new ShopCar(沫沫, iPhone7手机));
        shopCarDao.insert(new ShopCar(沫沫, 苹果));

        Address address1 = new Address(王荣俊, "广州市番禺区广州大学城XX学校XX宿舍");
        Address address2 = new Address(沫沫, "广州市天河区车陂冬景花园XX座XX号");
        addressDao.insert(address1);
        addressDao.insert(address2);

        Orders orders1 = new Orders(王荣俊, 三星E7手机, 1, 三星E7手机.getPrice(), "15521302230", "英俊", address1.getAddress(), "2016-04-14 15:34:12", 1);
        Orders orders2 = new Orders(王荣俊, 宏基笔记本, 1, 宏基笔记本.getPrice(), "15521302230", "英俊", address1.getAddress(), "2014-08-06 16:14:45", 1);
        Orders orders3 = new Orders(王荣俊, 菜心, 6, 菜心.getPrice() * 6, "15521302230", "英俊", address1.getAddress(), "2016-04-14 15:34:12", 0);
        Orders orders4 = new Orders(王荣俊, 辣条, 10, 辣条.getPrice() * 10, "15521302230", "英俊", address1.getAddress(), "2017-06-12 08:23:45", 0);
        Orders orders5 = new Orders(沫沫, iPhone7手机, 1, iPhone7手机.getPrice(), "13023796942", "沫沫", address2.getAddress(), "2015-03-04 21:54:23", 1);
        Orders orders6 = new Orders(沫沫, 宏基笔记本, 1, 宏基笔记本.getPrice(), "13023796942", "沫沫", address2.getAddress(), "2015-05-12 21:54:23", 1);
        Orders orders7 = new Orders(沫沫, 可乐, 2, 可乐.getPrice() * 2, "13023796942", "沫沫", address2.getAddress(), "2017-06-10 18:37:58", 0);
        Orders orders8 = new Orders(沫沫, 辣条, 20, 辣条.getPrice() * 20, "13023796942", "沫沫", address2.getAddress(), "2017-06-10 18:32:10", 0);

        ordersDao.insert(orders1);
        ordersDao.insert(orders2);
        ordersDao.insert(orders3);
        ordersDao.insert(orders4);
        ordersDao.insert(orders5);
        ordersDao.insert(orders6);
        ordersDao.insert(orders7);
        ordersDao.insert(orders8);

        evaluateDao.insert(new Evaluate(orders1, "三星E7用了很久了，质量很好！好评！", Evaluate.LEVEL_GOOD, "2016-05-12 09:21:42"));
        evaluateDao.insert(new Evaluate(orders2, "电脑一般吧，有时很卡", Evaluate.LEVEL_NORMAL, "2015-01-12 09:21:42"));
        evaluateDao.insert(new Evaluate(orders5, "苹果的ISO系统一直很快，装逼利器！", Evaluate.LEVEL_GOOD, "2015-03-07 15:34:12"));
        evaluateDao.insert(new Evaluate(orders6, "什么破电脑，买了没几天就坏了", Evaluate.LEVEL_BAD, "2015-05-22 15:34:12"));

        goodsImageDao.insert(new GoodsImage(宏基笔记本, "/admin/img/goods_1.jpg"));
        goodsImageDao.insert(new GoodsImage(宏基笔记本, "/admin/img/goods_2.jpg"));
        goodsImageDao.insert(new GoodsImage(宏基笔记本, "/admin/img/goods_3.jpg"));
        goodsImageDao.insert(new GoodsImage(宏基笔记本, "/admin/img/goods_4.jpg"));
        goodsImageDao.insert(new GoodsImage(宏基笔记本, "/admin/img/goods_5.jpg"));
        goodsImageDao.insert(new GoodsImage(苹果笔记本, "/admin/img/goods_6.jpg"));
        goodsImageDao.insert(new GoodsImage(苹果笔记本, "/admin/img/goods_7.jpg"));
        goodsImageDao.insert(new GoodsImage(苹果笔记本, "/admin/img/goods_8.jpg"));
        goodsImageDao.insert(new GoodsImage(三星E7手机, "/admin/img/goods_9.jpg"));
        goodsImageDao.insert(new GoodsImage(三星E7手机, "/admin/img/goods_10.jpg"));
        goodsImageDao.insert(new GoodsImage(iPhone7手机, "/admin/img/goods_11.jpg"));
        goodsImageDao.insert(new GoodsImage(iPhone7手机, "/admin/img/goods_12.jpg"));
        goodsImageDao.insert(new GoodsImage(菜心, "/admin/img/goods_13.jpg"));
        goodsImageDao.insert(new GoodsImage(菜心, "/admin/img/goods_14.jpg"));
        goodsImageDao.insert(new GoodsImage(菜心, "/admin/img/goods_15.jpg"));
        goodsImageDao.insert(new GoodsImage(苹果, "/admin/img/goods_16.jpg"));
        goodsImageDao.insert(new GoodsImage(辣条, "/admin/img/goods_17.jpg"));
        goodsImageDao.insert(new GoodsImage(可乐, "/admin/img/goods_18.jpg"));
        goodsImageDao.insert(new GoodsImage(可乐, "/admin/img/goods_19.jpg"));

        // 添加额外的无意义数据
        Shop shop = new Shop(张三, "张三的商店", "张三的商店的描述", "/admin/img/shop_1.jpg");
        shopDao.insert(shop);
        for (int i = 1; i <= 103; i++) {
            Goods goods = new Goods("商品" + i, "商品描述" + i,
                    i, goodsTypeDao.queryById(i % 10 + 1), shop, 500 + i * 100);
            goodsDao.insert(goods);
            GoodsImage goodsImage = new GoodsImage(goods, "/admin/img/goods_" + (i % 19 + 1) + ".jpg");
            goodsImageDao.insert(goodsImage);
        }

        session.close();
    }

}
