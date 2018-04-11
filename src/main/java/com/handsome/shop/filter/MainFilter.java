package com.handsome.shop.filter;

import com.wangrj.java_lib.java_util.LogUtil;
import com.wangrj.java_lib.java_util.TextUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * by wangrongjun on 2017/6/21.
 */
public class MainFilter implements Filter {

    private Map<String, String> map = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //以下文件都禁止直接访问，需要返回首页
        map.put("/index.jsp", "showIndex.do");
        map.put("/goods_box.jsp", "showIndex.do");

        //以下文件都禁止直接访问，只能交由xxxAction处理，即改为访问*.do
        map.put("/search_goods.jsp", "search.do");
        map.put("/search_goods_type.jsp", "searchType.do");
        map.put("/goods_info.jsp", "queryGoodsInfo.do");
        map.put("/shop_car.jsp", "showShopCar.do");
        map.put("/shop_detail.jsp", "queryShopDetail.do");
        map.put("/customer_order_list.jsp", "queryCustomerOrderList.do");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();//得到访问的servlet或者jsp的路径
        String doPath = map.get(servletPath);
        LogUtil.print(servletPath + " --> " + doPath);
        if (!TextUtil.isEmpty(doPath)) {
            request.getRequestDispatcher(doPath).forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
