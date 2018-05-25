package com.tctf.mapper;

import com.tctf.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserMapper {

    @Transactional
    void InsertUser(User user);

    boolean UpdateHeadurl(@Param("headurl") String headurl, @Param("uid") Integer uid);

    User Login(@Param("username")String username, @Param("password") String password);

    int SelectIdByUsername(String username);

    User FindOne(@Param("uid") Integer uid);

}
