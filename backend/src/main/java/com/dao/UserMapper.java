package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {


    //数据库中是否有这个username
    User getUsername(String username);

    //注册用户， 向数据库插入
    int rigister(@Param("username") String username,@Param("password") String password);

    //登录验证
    User login(@Param("username") String username, @Param("password") String password);

    //向用户id添加景点id
    int Addsite(Map map);


    //取出collectsite字符串
    String getCollect(@Param("id") int id);
    //用户id去掉景点id
    int deletesite(Map map);
    //更新collectsite字符串
    int setCollect(@Param("userId") int id,@Param("collectsite")String s);


    //根据用户id查找用户用户Username
    String findUsername(@Param("userId") int userId);

    //根据用户id取出collectfood字符串
    String getCollectFood(@Param("id") int id);

    //更新collectfood字符串
    int setCollectFood(@Param("userId") int userId, @Param("collectfood")String s);

    int Addfood(Map map);

}
