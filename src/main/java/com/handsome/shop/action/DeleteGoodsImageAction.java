package com.handsome.shop.action;

import com.handsome.shop.dao.GoodsImageDao;
import com.handsome.shop.dao.impl.GoodsImageDaoImpl;
import com.handsome.shop.framework.ActionSupport;
import com.wangrj.java_lib.java_util.GsonUtil;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * by wangrongjun on 2017/9/29.
 */
public class DeleteGoodsImageAction extends ActionSupport {

    @Override
    protected String execute() throws ServletException, IOException,
            ParamErrorException, CustomerNotExistsException {

        String deleteIdList = checkStringParameter("deleteIdList");
        GoodsImageDao goodsImageDao = new GoodsImageDaoImpl();
        String[] split = deleteIdList.split(",");
        boolean succeedAll = true;
        long failId = 0;
        for (String s : split) {
            long id = Long.parseLong(s);
            if (!goodsImageDao.deleteById(id)) {
                succeedAll = false;
                failId = id;
                break;
            }
        }

        ResponseData responseData;
        if (succeedAll) {
            responseData = new ResponseData(0, "ok");
        } else {
            responseData = new ResponseData(-1, "fail to delete: id=" + failId);
        }

        String s = GsonUtil.toJson(responseData);
        response.getWriter().write(s);

        return null;
    }

    static class ResponseData {
        int stateCode;// 0为成功，-1为失败，-2为异常
        String msg;

        public ResponseData(int stateCode, String msg) {
            this.stateCode = stateCode;
            this.msg = msg;
        }
    }

}
