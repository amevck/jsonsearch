package com.swivelgroup.jsonsearch.exception;

public class JsonSearchException extends Exception {
    public JsonSearchException(Exception e){
        super(e);
    }
    public JsonSearchException(String msg){
        super(msg);
    }
}
