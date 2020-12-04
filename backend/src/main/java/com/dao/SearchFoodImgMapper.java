package com.dao;

import com.pojo.SearchFoodImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchFoodImgMapper {

    List<SearchFoodImg> getSearchFoodImg(@Param("cityId") int cityId);


    SearchFoodImg getCollectFoodById(@Param("siteId") int id);
}
