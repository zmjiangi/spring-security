package com.gzyijian.springsecurity.repository;

import com.gzyijian.springsecurity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zmjiangi on 2018-5-11.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
