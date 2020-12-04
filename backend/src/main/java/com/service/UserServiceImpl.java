package com.service;

import com.dao.UserMapper;
import com.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements  UserService{


    @Autowired
    UserMapper userMapper;

    @Override
    public User getUsername(String username) {
        return userMapper.getUsername(username);
    }

    @Override
    public int rigister(@Param("username") String username, @Param("password") String password) {
        return userMapper.rigister(username, password);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public int Addsite(Map map) {
        return userMapper.Addsite(map);
    }

    @Override
    public String getCollect(@Param("id") int id) {
        return userMapper.getCollect(id);
    }

    @Override
    public int setCollect(int id, String s) {
        return userMapper.setCollect(id, s);
    }

    @Override
    public String findUsername(int userId) {
        return userMapper.findUsername(userId);
    }

    @Override
    public String getCollectFood(int id) {
        return userMapper.getCollectFood(id);
    }

    @Override
    public int setCollectFood(int userId, String s) {
        return userMapper.setCollectFood(userId, s);
    }

    @Override
    public int Addfood(Map map) {
        return userMapper.Addfood(map);
    }


}
