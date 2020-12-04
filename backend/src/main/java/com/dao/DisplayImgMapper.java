package com.dao;

import com.pojo.DisplayImg;

import java.util.List;

public interface DisplayImgMapper {
    List<DisplayImg> getDisplayImg();


    DisplayImg getInfo(int id);
}
