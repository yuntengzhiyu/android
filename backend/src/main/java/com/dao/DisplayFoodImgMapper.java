package com.dao;

import com.pojo.DisplayFoodImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DisplayFoodImgMapper {
    List<DisplayFoodImg> getDisplayFoodImg();

    //根据Id 返回食品信息
    DisplayFoodImg getDisplayFoodImgInfo(@Param("id") int id);



}
