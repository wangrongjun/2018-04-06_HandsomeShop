package com.handsome.shop.util;

/**
 * by wangrongjun on 2018/5/1.
 */
public class RequestStatus {

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    public static final int ERROR = -2;
    public static final int EXCEPTION = -3;

    private int statusCode;
    private String msg;
    private Exception e;

    public RequestStatus(int statusCode, String msg, Exception e) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.e = e;
    }

    public static RequestStatus success() {
        return new RequestStatus(SUCCESS, null, null);
    }

    public static RequestStatus success(String msg) {
        return new RequestStatus(SUCCESS, msg, null);
    }

    public static RequestStatus fail(String msg) {
        return new RequestStatus(FAIL, msg, null);
    }

    public static RequestStatus error(String msg) {
        return new RequestStatus(ERROR, msg, null);
    }

    public static RequestStatus exception(Exception e) {
        return new RequestStatus(EXCEPTION, e.toString(), e);
    }

}
