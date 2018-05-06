package com.handsome.shop.test;

import com.wangrj.java_lib.java_util.GsonUtil;
import com.wangrj.java_lib.java_util.HttpRequest;

/**
 * by wangrongjun on 2017/11/3.
 */
public class TestClass {

    public static void main(String[] args) {
        HttpRequest.Response response = new HttpRequest().
                setRequestMethod("GET").
//                setRequestBody("goodsId=17&count=1&contactId=81".getBytes()).
                request("http://localhost:8080/rest/orders/customer/25");
        GsonUtil.printFormatJson(response.getResponseData());
    }

}
