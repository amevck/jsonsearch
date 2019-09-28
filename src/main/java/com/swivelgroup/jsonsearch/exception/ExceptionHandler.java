package com.swivelgroup.jsonsearch.exception;

import com.swivelgroup.jsonsearch.JsonsearchApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler extends java.lang.RuntimeException {
    /** The Constant serialVersionUID. */
    private  Logger logger = LoggerFactory.getLogger(JsonsearchApplication.class);

    public ExceptionHandler(Exception e) {
        super(e);
        logger.error(e.toString());
    }

    public ExceptionHandler(String msg) {
        super(msg);
        logger.error(msg);
    }
}
