package com.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchImgInfo {
    int id;
    String  siteName,  siteAddress;
    List<String> sitePic;
    List<othersite> othersite;
    String sitePhone, siteIntro, sitePolicy;
}
