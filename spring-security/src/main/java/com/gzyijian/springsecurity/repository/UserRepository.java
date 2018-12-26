package com.gzyijian.springsecurity.repository;

import com.gzyijian.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zmjiangi
 * @date 2018-5-11
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

}
