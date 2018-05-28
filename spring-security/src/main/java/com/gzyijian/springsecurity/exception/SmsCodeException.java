package com.gzyijian.springsecurity.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by zmjiangi on 2018-5-28.
 */
public class SmsCodeException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public SmsCodeException(String msg) {
        super(msg);
    }

}
