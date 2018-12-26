package com.gzyijian.springsecurity.rescontroller;

import com.gzyijian.springsecurity.model.SmsCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zmjiangi
 * @date 2018-5-28
 */
@RestController
public class SmsCodeRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsCodeRestController.class);
    public static final String SESSION_KEY = "SESSION_KEY_SMS";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/api/smsCode")
    public void send(HttpServletRequest request) {
        SmsCode smsCode = createSmsCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
    }

    private SmsCode createSmsCode() {
        String code = RandomStringUtils.randomNumeric(6);
        LOGGER.info("发送的短信验证码是" + code);
        SmsCode smsCode = new SmsCode(code, 600);
        return smsCode;
    }

}
