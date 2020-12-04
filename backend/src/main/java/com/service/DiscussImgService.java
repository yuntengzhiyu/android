package com.service;

import com.pojo.DiscussImg;

import java.util.List;

public interface DiscussImgService {
    List<DiscussImg> getDiscussImg();
   DiscussImg getDiscussImgInfo(int id);
}
