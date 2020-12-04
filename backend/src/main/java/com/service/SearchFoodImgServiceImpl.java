package com.service;

import com.dao.SearchFoodImgMapper;
import com.pojo.SearchFoodImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFoodImgServiceImpl implements SearchFoodImgService{
    @Autowired
    SearchFoodImgMapper searchFoodImgMapper;
    @Override
    public List<SearchFoodImg> getSearchFoodImg(int cityId) {
        return searchFoodImgMapper.getSearchFoodImg(cityId);
    }

    @Override
    public SearchFoodImg getCollectFoodById(int id) {
        return searchFoodImgMapper.getCollectFoodById(id);
    }
}
