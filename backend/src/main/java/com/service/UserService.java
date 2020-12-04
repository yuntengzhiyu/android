package com.service;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {

    User getUsername(String username);

    int rigister(@Param("username") String username, @Param("password") String password);

    User login(@Param("username") String username, @Param("password") String password);

    int Addsite(Map map);

    String getCollect(@Param("id") int id);

    int setCollect(@Param("userId") int id,@Param("collectsite")String s);

    String findUsername(@Param("userId") int userId);


    String getCollectFood(@Param("id") int id);

    int setCollectFood(@Param("userId") int userId, @Param("collectfood")String s);

    int Addfood(Map map);
}
