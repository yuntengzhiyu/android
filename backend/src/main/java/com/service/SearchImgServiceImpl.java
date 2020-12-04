package com.service;

import com.dao.SearchImgMapper;
import com.pojo.Collectsite;
import com.pojo.SearchImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchImgServiceImpl implements SearchImgService{

    @Autowired
    SearchImgMapper searchImgMapper;

    @Override
    public List<SearchImg> getSearchImg(int cityId) {
        return searchImgMapper.getSearchImg(cityId);
    }

    @Override
    public SearchImg getSearchImgInfo(int id) {
        return searchImgMapper.getSearchImgInfo(id);
    }

    @Override
    public Collectsite getCollectSiteById(int id) {
        return searchImgMapper.getCollectSiteById(id);
    }
}
