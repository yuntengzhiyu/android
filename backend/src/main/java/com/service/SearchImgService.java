package com.service;

import com.pojo.Collectsite;
import com.pojo.SearchImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchImgService {
    List<SearchImg> getSearchImg(int cityId);


    SearchImg getSearchImgInfo(int id);

    Collectsite getCollectSiteById(@Param("id") int id);
}
