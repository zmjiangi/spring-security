package com.gzyijian.springsecurity.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zmjiangi
 * @date 2018-5-28
 */
public class SmsCode implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private LocalDateTime expireTime;

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
