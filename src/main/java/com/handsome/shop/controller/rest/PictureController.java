package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.PictureDao;
import com.handsome.shop.entity.Picture;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.util.PictureTypeUtil;
import com.wangrj.java_lib.java_util.StreamUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * by wangrongjun on 2018/5/10.
 */
@Controller
@RequestMapping("/rest/picture/{pictureId}")
public class PictureController extends BaseController {

    @Resource
    private PictureDao pictureDao;


    @GetMapping
    public void getPicture(HttpServletResponse response, @PathVariable int pictureId) throws SQLException, IOException {
        Picture picture = pictureDao.queryById(pictureId);
        response.setHeader("Content-Type", PictureTypeUtil.toContentType(picture.getPictureType().toString()));
        byte[] bytes = StreamUtil.toBytes(picture.getPictureData().getBinaryStream());
        response.getOutputStream().write(bytes);
    }

}
