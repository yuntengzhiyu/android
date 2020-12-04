package com.service;

import com.pojo.DisplayFoodImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DisplayFoodImgService {
    List<DisplayFoodImg> getDisplayFoodImg();

    DisplayFoodImg getDisplayFoodImgInfo(@Param("id") int id);

}
