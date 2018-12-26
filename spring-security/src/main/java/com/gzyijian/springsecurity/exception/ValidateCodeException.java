package com.gzyijian.springsecurity.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author zmjiangi
 * @date 2018-5-25
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
