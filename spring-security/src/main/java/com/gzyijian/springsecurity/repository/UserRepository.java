package com.gzyijian.springsecurity.repository;

import com.gzyijian.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zmjiangi on 2018-5-11.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
