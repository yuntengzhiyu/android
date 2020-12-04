package com.service;

import com.dao.DisplayImgMapper;
import com.pojo.DisplayImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayImgServiceImpl implements DisplayImgService{

    @Autowired
    private DisplayImgMapper displayImgMapper;


    public List<DisplayImg> getDisplayImg() {
        return displayImgMapper.getDisplayImg();
    }

    @Override
    public DisplayImg getInfo(int id) {
        return displayImgMapper.getInfo(id);
    }
}
