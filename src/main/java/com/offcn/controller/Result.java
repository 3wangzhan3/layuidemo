package com.offcn.controller;

import java.util.List;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }
    public Result<T> tranSuccess(String msg){
        return new Result<>(1,msg);
    }

    public Result<T> tranSuccess(String msg, T data){
        return new Result<>(1,msg,data);
    }

    public Result<T> tranError(String msg){
        return new Result<>(0,msg);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
