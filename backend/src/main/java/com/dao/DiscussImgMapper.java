package com.dao;

import com.pojo.DiscussImg;

import java.util.List;

public interface DiscussImgMapper {

    List<DiscussImg> getDiscussImg();

    DiscussImg getDiscussImgInfo(int id);
}
