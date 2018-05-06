package com.handsome.shop.util;

/**
 * by wangrongjun on 2018/5/1.
 */
public class RequestStatus<T> {

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    public static final int ERROR = -2;
    public static final int EXCEPTION = -3;

    private int status;
    private String msg;
    private T data;
    private Exception e;

    public RequestStatus(int status, String msg, T data, Exception e) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.e = e;
    }

    public static <T> RequestStatus<T> success() {
        return new RequestStatus<T>(SUCCESS, null, null, null);
    }

    public static <T> RequestStatus<T> success(String msg) {
        return new RequestStatus<>(SUCCESS, msg, null, null);
    }

    public static <T> RequestStatus<T> success(T data) {
        return new RequestStatus<>(SUCCESS, null, data, null);
    }

    public static <T> RequestStatus<T> success(String msg, T data) {
        return new RequestStatus<>(SUCCESS, msg, data, null);
    }

    public static <T> RequestStatus<T> fail() {
        return new RequestStatus<T>(FAIL, null, null, null);
    }

    public static <T> RequestStatus<T> fail(String msg) {
        return new RequestStatus<T>(FAIL, msg, null, null);
    }

    public static <T> RequestStatus<T> error(String msg) {
        return new RequestStatus<>(ERROR, msg, null, null);
    }

    public static <T> RequestStatus<T> exception(Exception e) {
        return new RequestStatus<>(EXCEPTION, e.toString(), null, e);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
