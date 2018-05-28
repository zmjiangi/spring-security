package com.gzyijian.springsecurity.filter;

import com.gzyijian.springsecurity.authentication.AuthenticationFailureHandler;
import com.gzyijian.springsecurity.exception.ValidateCodeException;
import com.gzyijian.springsecurity.model.SmsCode;
import com.gzyijian.springsecurity.rescontroller.SmsCodeRestController;
import com.gzyijian.springsecurity.rescontroller.ValidateCodeRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.isEmpty;
import static org.springframework.web.bind.ServletRequestUtils.getStringParameter;

/**
 * Created by zmjiangi on 2018-5-28.
 */
@Component
public class SmsCodeFilter extends OncePerRequestFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        if ("/login".equals(requestURI) && "post".equalsIgnoreCase(method)) {
            try {
                this.validate(new ServletWebRequest(request));
                LOGGER.info("短信验证码校验通过");
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        SmsCode codeInSession = (SmsCode) sessionStrategy.getAttribute(
                request,
                SmsCodeRestController.SESSION_KEY
        );
        String codeInRequest = getStringParameter(request.getRequest(), "smsCode");
        if (isEmpty(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeRestController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!codeInSession.getCode().equalsIgnoreCase(codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, SmsCodeRestController.SESSION_KEY);
    }
}
