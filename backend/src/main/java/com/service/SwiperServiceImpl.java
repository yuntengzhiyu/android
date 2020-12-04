package com.service;

import com.dao.SwiperMapper;
import com.pojo.swiper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwiperServiceImpl implements SwiperService{

    @Autowired
    SwiperMapper swiperMapper;

    @Override
    public List<swiper> getSwiper() {
        return swiperMapper.getSwiper();
    }
}
