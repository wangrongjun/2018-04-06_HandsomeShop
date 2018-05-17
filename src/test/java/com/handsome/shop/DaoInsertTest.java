package com.handsome.shop;

import com.handsome.shop.dao.*;
import com.handsome.shop.entity.*;
import com.wangrj.java_lib.java_util.DateUtil;
import com.wangrj.java_lib.java_util.StreamUtil;
import com.wangrj.java_lib.java_util.TextUtil;
import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.*;

/**
 * by wangrongjun on 2017/6/17.
 */
public class DaoInsertTest extends BaseDaoTest {

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
    private ContactDao addressDao;
    @Resource
    private PictureDao pictureDao;
    @Resource
    private RefundDao refundDao;

    @BeforeClass
    public static void rebuildDatabase() throws IOException {
        Process process = Runtime.getRuntime().exec("cmd /c cd src/main/resources/sql && mysql -uroot -p21436587 < rebuild_db.sql");
        System.out.println(StreamUtil.readInputStream(process.getInputStream()));
        System.out.println("---------------------");
        System.out.println(StreamUtil.readInputStream(process.getErrorStream()));
        System.out.println("---------------------");
        System.out.println("exitValue: " + process.exitValue());
    }

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
    public void testInsert() throws IOException {
        GoodsType 电子产品 = new GoodsType("电子产品");
        GoodsType 笔记本 = new GoodsType("笔记本");
        GoodsType 游戏机 = new GoodsType("游戏机");
        GoodsType 手机 = new GoodsType("手机");
        GoodsType 安卓手机 = new GoodsType("安卓手机");
        GoodsType 苹果手机 = new GoodsType("苹果手机");
        GoodsType WinPhone手机 = new GoodsType("WinPhone手机");

        GoodsType 食品 = new GoodsType("食品");
        GoodsType 蔬菜 = new GoodsType("蔬菜");
        GoodsType 水果 = new GoodsType("水果");
        GoodsType 零食 = new GoodsType("零食");
        GoodsType 饮料 = new GoodsType("饮料");

        goodsTypeDao.insert(电子产品);
        goodsTypeDao.insert(笔记本);
        goodsTypeDao.insert(游戏机);
        goodsTypeDao.insert(手机);
        goodsTypeDao.insert(安卓手机);
        goodsTypeDao.insert(苹果手机);
        goodsTypeDao.insert(WinPhone手机);
        goodsTypeDao.insert(食品);
        goodsTypeDao.insert(蔬菜);
        goodsTypeDao.insert(水果);
        goodsTypeDao.insert(零食);
        goodsTypeDao.insert(饮料);

        goodsTypeRelationDao.addRelation(电子产品, 笔记本);
        goodsTypeRelationDao.addRelation(电子产品, 游戏机);
        goodsTypeRelationDao.addRelation(电子产品, 手机);
        goodsTypeRelationDao.addRelation(手机, 安卓手机);
        goodsTypeRelationDao.addRelation(手机, 苹果手机);
        goodsTypeRelationDao.addRelation(手机, WinPhone手机);

        goodsTypeRelationDao.addRelation(食品, 蔬菜);
        goodsTypeRelationDao.addRelation(食品, 水果);
        goodsTypeRelationDao.addRelation(食品, 零食);
        goodsTypeRelationDao.addRelation(食品, 饮料);

        Seller 张三 = new Seller("210", "123", "张三", "明月公主", "女", pic("seller_1.jpg"));
        Seller 李四 = new Seller("220", "123", "李四", "型男", "男", pic("seller_2.jpg"));
        Shop 东方电脑城 = new Shop(张三, "东方电脑城", "专卖电脑及其设备", pic("shop_1.jpg"));
        Shop 手机旗舰店 = new Shop(张三, "手机旗舰店", "出售最新的三星，苹果手机", pic("shop_2.jpg"));
        Shop 生鲜店 = new Shop(李四, "生鲜店", "各种新鲜水果，蔬菜应有尽有！", pic("shop_3.jpg"));
        Shop 零食店 = new Shop(李四, "零食店", "吃货预备营！", pic("shop_4.jpg"));

        Goods 宏基笔记本 = new Goods("宏基笔记本", "Aspire最新版", 3200, 20, 笔记本, 东方电脑城, pics("goods_1.jpg", "goods_2.jpg", "goods_3.jpg", "goods_4.jpg", "goods_5.jpg"));
        Goods 苹果笔记本 = new Goods("苹果笔记本", "超薄迷你", 5400, 60, 笔记本, 东方电脑城, pics("goods_6.jpg", "goods_7.jpg", "goods_8.jpg"));
        Goods 三星E7手机 = new Goods("三星E7手机", "E7系列", 1500, 38, 安卓手机, 手机旗舰店, pics("goods_9.jpg", "goods_10.jpg"));
        Goods iPhone7手机 = new Goods("iPhone7手机", "乔布斯呕心沥血之作！", 5000, 8, 苹果手机, 手机旗舰店, pics("goods_11.jpg", "goods_12.jpg"));
        Goods 菜心 = new Goods("菜心", "新鲜的菜心一斤，纯天然无农药", 7.5, 200, 蔬菜, 生鲜店, pics("goods_13.jpg", "goods_14.jpg", "goods_15.jpg"));
        Goods 苹果 = new Goods("苹果", "新鲜的苹果一斤，纯天然无农药", 11.2, 80, 水果, 生鲜店, pics("goods_16.jpg"));
        Goods 辣条 = new Goods("辣条", "老外都抢着吃，欲购从速！", 2.5, 250, 零食, 零食店, pics("goods_17.jpg"));
        Goods 可乐 = new Goods("可乐", "透心凉，心飞扬！", 5, 300, 饮料, 零食店, pics("goods_18.jpg", "goods_19.jpg"));

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

        Customer 王荣俊 = new Customer("110", "123", "王荣俊", "英俊", "男", pic("customer_1.jpg"));
        Customer 沫沫 = new Customer("120", "123", "沫沫", "沫宝儿", "女", pic("customer_2.jpg"));

        customerDao.insert(王荣俊);
        customerDao.insert(沫沫);

        shopCarDao.insert(new ShopCar(王荣俊, iPhone7手机));
        shopCarDao.insert(new ShopCar(王荣俊, 苹果笔记本));
        shopCarDao.insert(new ShopCar(沫沫, iPhone7手机));
        shopCarDao.insert(new ShopCar(沫沫, 苹果));

        Contact contact1 = new Contact(王荣俊, "13710512633", "柏林", "广州市番禺区沙头大大", true);
        Contact contact2 = new Contact(王荣俊, "15521302233", "王荣俊", "广州市番禺区广州大学城XX学校XX宿舍", false);
        Contact contact3 = new Contact(沫沫, "13023796942", "沫沫", "广州市天河区车陂冬景花园XX座XX号", true);
        addressDao.insert(contact1);
        addressDao.insert(contact2);
        addressDao.insert(contact3);

        Orders orders1 = new Orders(王荣俊, 三星E7手机, 1, 三星E7手机.getPrice(), contact1, "备注1", Orders.Status.Created);
        Orders orders2 = new Orders(王荣俊, 宏基笔记本, 1, 宏基笔记本.getPrice(), contact1, "备注2", Orders.Status.Pending_Receive);
        Orders orders3 = new Orders(王荣俊, 菜心, 6, 菜心.getPrice() * 6, contact2, null, Orders.Status.Received);
        Orders orders4 = new Orders(王荣俊, 辣条, 10, 辣条.getPrice() * 10, contact2, null, Orders.Status.Pending_Refund);
        Orders orders5 = new Orders(沫沫, iPhone7手机, 1, iPhone7手机.getPrice(), contact3, "备注1", Orders.Status.Closed);
        Orders orders6 = new Orders(沫沫, 宏基笔记本, 1, 宏基笔记本.getPrice(), contact3, "备注2", Orders.Status.Finish);
        Orders orders7 = new Orders(沫沫, 可乐, 2, 可乐.getPrice() * 2, contact3, null, Orders.Status.Created);
        Orders orders8 = new Orders(沫沫, 辣条, 20, 辣条.getPrice() * 20, contact3, null, Orders.Status.Created);

        orders1.setCreatedOn(d("2016-04-14 15:34:12"));
        orders2.setCreatedOn(d("2014-08-06 16:14:45"));
        orders3.setCreatedOn(d("2016-04-14 15:34:12"));
        orders4.setCreatedOn(d("2017-06-12 08:23:45"));
        orders5.setCreatedOn(d("2015-03-04 21:54:23"));
        orders6.setCreatedOn(d("2015-05-12 21:54:23"));
        orders7.setCreatedOn(d("2017-06-10 18:37:58"));
        orders8.setCreatedOn(d("2017-06-10 18:32:10"));

        ordersDao.insert(orders1);
        ordersDao.insert(orders2);
        ordersDao.insert(orders3);
        ordersDao.insert(orders4);
        ordersDao.insert(orders5);
        ordersDao.insert(orders6);
        ordersDao.insert(orders7);
        ordersDao.insert(orders8);

        refundDao.insert(new Refund(orders4, "不喜欢辣条", pics("goods_11.jpg", "goods_12.jpg")));
        refundDao.insert(new Refund(orders5, "不喜欢iPhone7手机", pics("goods_17.jpg")));

        Evaluate evaluate1 = new Evaluate(orders1, "三星E7用了很久了，质量很好！好评！", Evaluate.Level.Good);
        Evaluate evaluate2 = new Evaluate(orders2, "电脑一般吧，有时很卡", Evaluate.Level.Normal);
        Evaluate evaluate3 = new Evaluate(orders5, "苹果的ISO系统一直很快，装逼利器！", Evaluate.Level.Good);
        Evaluate evaluate4 = new Evaluate(orders6, "什么破电脑，买了没几天就坏了", Evaluate.Level.Bad);
        evaluate1.setCreatedOn(d("2016-05-12 09:21:42"));
        evaluate2.setCreatedOn(d("2015-01-12 09:21:42"));
        evaluate3.setCreatedOn(d("2015-03-07 15:34:12"));
        evaluate4.setCreatedOn(d("2015-05-22 15:34:12"));
        evaluateDao.insert(evaluate1);
        evaluateDao.insert(evaluate2);
        evaluateDao.insert(evaluate3);
        evaluateDao.insert(evaluate4);

        // 添加额外的无意义数据
        for (int i = 1; i <= 50; i++) {
            Customer customer = new Customer("155" + i, "123", "user" + i, "nick" + i, i % 4 == 0 ? "男" : "女",
                    pic("customer_" + (i % 2 + 1) + ".jpg"));
            customerDao.insert(customer);
        }
        Shop shop = new Shop(张三, "张三的商店", "张三的商店的描述", pic("shop_1.jpg"));
        shopDao.insert(shop);
        for (int i = 1; i <= 103; i++) {
            Goods goods = new Goods("商品" + i, "商品描述" + i, 500 + i * 100, i,
                    goodsTypeDao.queryById(i % 5 + 1), shop, pics("goods_" + (i % 19 + 1) + ".jpg"));
            goodsDao.insert(goods);
        }

    }

    private Date d(String dateTime) {
        return DateUtil.toDate(dateTime);
    }

    private List<InputStream> inputStreamList = new ArrayList<>();

    private Picture pic(String fileName) throws IOException {
        Picture.PictureType pictureType = Picture.PictureType.valueOf(TextUtil.getTextAfterLastPoint(fileName));
        FileInputStream fis = new FileInputStream("src/main/webapp/admin/img/" + fileName);
        Blob pictureData = Hibernate.getLobCreator(session).createBlob(fis, fis.available());
        inputStreamList.add(fis);
        Picture picture = new Picture(pictureType, pictureData);
        pictureDao.insert(picture);
        return picture;
    }

    private Set<Picture> pics(String... fileNameList) throws IOException {
        Set<Picture> pictureSet = new HashSet<>();
        for (String fileName : fileNameList) {
            pictureSet.add(pic(fileName));
        }
        return pictureSet;
    }

    @After
    public void closeInputStream() {
        for (InputStream is : inputStreamList) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
