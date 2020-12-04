package com.dao;

import com.pojo.Collectsite;
import com.pojo.SearchImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchImgMapper {

    List<SearchImg> getSearchImg(int cityId);


    SearchImg getSearchImgInfo(int id);


        //根据景点id获取collectsite数据
    Collectsite getCollectSiteById(@Param("id") int id);

}
