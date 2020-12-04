package com.service;

import com.dao.FoodSwiperMapper;
import com.pojo.FoodSwiper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodSwiperServiceImpl implements  FoodSwiperService{


    @Autowired
    FoodSwiperMapper foodSwiperMapper;


    @Override
    public List<FoodSwiper> getFoodSwiper() {
        return foodSwiperMapper.getFoodSwiper();
    }
}
