package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFoodImg {
    int cityId, siteId, commentsNum;
    String cityName, imgPath, title, score, address, avgPrice;
}
