package com.service;

import com.pojo.DisplayImg;

import java.util.List;

public interface DisplayImgService {
    List<DisplayImg> getDisplayImg();


    DisplayImg getInfo(int id);
}
