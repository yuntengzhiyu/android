package com.service;

import com.pojo.DiscussFoodImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussFoodImgService {
    List<DiscussFoodImg> getDiscussFoodImg();

    DiscussFoodImg getDiscussFoodImgInfo(@Param("id") int id);
}
