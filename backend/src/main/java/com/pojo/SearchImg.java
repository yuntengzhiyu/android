package com.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchImg {
    int id, cityId , collect;
    String cityName, siteName, commentsNum, commentsPoint, siteAddress;
    String sitePhone, sitePic,siteIntro, sitePolicy, othersitePic, othersiteName, othersiteDis;
}
