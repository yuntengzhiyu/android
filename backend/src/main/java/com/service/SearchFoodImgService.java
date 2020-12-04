package com.service;

import com.pojo.SearchFoodImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchFoodImgService {

    List<SearchFoodImg> getSearchFoodImg(int cityId);


    SearchFoodImg getCollectFoodById(@Param("siteId") int id);
}
