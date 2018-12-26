package com.gzyijian.springsecurity.config;

import com.gzyijian.springsecurity.filter.SmsCodeFilter;
import com.gzyijian.springsecurity.filter.ValidateCodeFilter;
import com.gzyijian.springsecurity.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author zmjiangi
 * @date 2018-5-11
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private SmsCodeFilter smsCodeFilter;


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 启动时创建表
///        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * 定义安全策略
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                // 配置安全策略
                .authorizeRequests()
                // 定义不需要验证请求
                .antMatchers("/login", "/logout", "/api/validateCode", "/api/smsCode")
                .permitAll()
                // 其余的所有请求都需要验证
                .anyRequest()
                .authenticated()
                .and()
                // 过滤器
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 使用form表单登录
                .formLogin()
                // 定义登录页
                .loginPage("/login")
                // 定义登录错误页
                .failureUrl("/login?error")
                .and()
                // 记住我
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                // token有效时间, 单位秒
                .tokenValiditySeconds(3600)
                .userDetailsService(userService)
                .and()
                // 登出url
                .logout()
                .logoutUrl("/logout")
                // 登出成功重定向url
                .logoutSuccessUrl("/login");
    }

}
