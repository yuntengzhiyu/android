package com.dao;

import com.pojo.DiscussFoodImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DiscussFoodImgMapper {

    List<DiscussFoodImg> getDiscussFoodImg();

    //根据id获取文章内容
    DiscussFoodImg getDiscussFoodImgInfo(@Param("id") int id);
}



