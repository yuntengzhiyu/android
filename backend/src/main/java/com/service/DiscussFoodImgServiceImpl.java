package com.service;

import com.dao.DiscussFoodImgMapper;
import com.pojo.DiscussFoodImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussFoodImgServiceImpl implements DiscussFoodImgService{

    @Autowired
    DiscussFoodImgMapper discussFoodImgMapper;

    @Override
    public List<DiscussFoodImg> getDiscussFoodImg() {
        return discussFoodImgMapper.getDiscussFoodImg();
    }

    @Override
    public DiscussFoodImg getDiscussFoodImgInfo(int id) {
        return discussFoodImgMapper.getDiscussFoodImgInfo(id);
    }
}
