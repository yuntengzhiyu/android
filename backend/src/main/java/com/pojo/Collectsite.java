package com.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collectsite {
    int id, cityId;
    String siteName, commentsNum, commentsPoint, sitePic;
}
