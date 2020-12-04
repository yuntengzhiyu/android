package com.service;

import com.dao.DisplayFoodImgMapper;
import com.dao.DisplayImgMapper;
import com.pojo.DiscussImg;
import com.pojo.DisplayFoodImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayFoodImgServiceImpl implements  DisplayFoodImgService{

    @Autowired
    DisplayFoodImgMapper displayFoodImgMapper;

    @Override
    public List<DisplayFoodImg> getDisplayFoodImg() {
        return displayFoodImgMapper.getDisplayFoodImg();
    }

    @Override
    public DisplayFoodImg getDisplayFoodImgInfo(int id) {
        return displayFoodImgMapper.getDisplayFoodImgInfo(id);
    }
}
