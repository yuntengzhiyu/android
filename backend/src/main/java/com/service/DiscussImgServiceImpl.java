package com.service;

import com.dao.DiscussImgMapper;
import com.pojo.DiscussImg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussImgServiceImpl implements DiscussImgService {

    @Autowired
    private DiscussImgMapper discussImgMapper;

    public List<DiscussImg> getDiscussImg() {
        return discussImgMapper.getDiscussImg();
    }

    @Override
    public DiscussImg getDiscussImgInfo(int id) {
        return discussImgMapper.getDiscussImgInfo(id);
    }
}
