package com.handsome.shop.util;

import com.handsome.shop.entity.Picture;

/**
 * by wangrongjun on 2018/5/23.
 */
public class PictureTypeUtil {

    public static String toContentType(String pictureExtendName) {
        switch (pictureExtendName) {
            case "bmp":
                return "application/x-bmp";
            case "gif":
                return "image/gif";
            case "ico":
                return "image/x-icon";
            case "jpg":
                return "image/jpeg";
            case "png":
                return "image/png";
            default:
                throw new RuntimeException("not support pictureExtendName of picture: " + pictureExtendName);
        }
    }

    public static Picture.PictureType toPictureType(String contentType) {
        switch (contentType) {
            case "application/x-bmp":
                return Picture.PictureType.bmp;
            case "image/gif":
                return Picture.PictureType.gif;
            case "image/x-ico":
                return Picture.PictureType.ico;
            case "image/jpeg":
                return Picture.PictureType.jpg;
            case "image/png":
                return Picture.PictureType.png;
            default:
                throw new RuntimeException("not support content-type of picture: " + contentType);
        }
    }

}
