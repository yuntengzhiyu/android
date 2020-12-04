package com.control;


import com.mysql.fabric.Server;
import com.pojo.*;
import com.service.*;
import com.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@ResponseBody
public class FoodController {

    @Autowired
    @Qualifier("foodSwiperServiceImpl")
    FoodSwiperService foodSwiperService;

    @Autowired
    @Qualifier("displayFoodImgServiceImpl")
    DisplayFoodImgService displayFoodImgService;

    @Autowired
    @Qualifier("discussFoodImgServiceImpl")
    DiscussFoodImgService discussFoodImgService;

    @Autowired
    @Qualifier("searchFoodImgServiceImpl")
    SearchFoodImgService searchFoodImgService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("getfooddataimg")
    public ServerResponse getfooddataimg(@RequestParam("cityId") int cityId){
        List<SearchFoodImg> list = searchFoodImgService.getSearchFoodImg(cityId);
        List<String> siteName = new LinkedList<String>();
        List<Double> points = new LinkedList<Double>();
        List<Map<String,Object>> data = new LinkedList<Map<String, Object>>();
        List<Integer> price = new LinkedList<Integer>();
        for(SearchFoodImg searchFoodImg : list){
            Map<String,Object> map = new HashMap<String, Object>();
            String s = searchFoodImg.getTitle().substring(0,3);
            siteName.add(s);
            Double d = Double.parseDouble(searchFoodImg.getScore());
            points.add(d);
            map.put("name",s);
            map.put("data",searchFoodImg.getCommentsNum());
            price.add(Integer.parseInt(searchFoodImg.getAvgPrice()));
            data.add(map);
        }
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("siteName",siteName);
        map1.put("points",points);
        map1.put("data",data);
        map1.put("price",price);
        return ServerResponse.createBySuccess("成功获取数据",map1);
    }

    @RequestMapping("getFoodSwiper")
    public ServerResponse test1(){
        List<FoodSwiper> list = foodSwiperService.getFoodSwiper();
        return ServerResponse.createBySuccess("成功获取数据", list);
    }

    @RequestMapping("getDisplayFoodImg")
    public ServerResponse test2(){
        List<DisplayFoodImg> list = displayFoodImgService.getDisplayFoodImg();
        return ServerResponse.createBySuccess("成功获取数据", list);
    }

    @RequestMapping("getDisplayFoodImgInfo")
    public ServerResponse test3(@RequestParam("id") int id){
        DisplayFoodImg img = displayFoodImgService.getDisplayFoodImgInfo(id);
        DisplayFoodImgVO displayFoodImgVO = new DisplayFoodImgVO();
        displayFoodImgVO.setId(id);
        displayFoodImgVO.setTitle(img.getTitle());
        List<String> list = Arrays.asList(img.getOtherimg().split(";"));
        List<String> word = Arrays.asList(img.getOtherword().split(";"));
        List<article> list2 = new LinkedList<article>();
        for(int i = 0; i < list.size(); ++i){
            article arti = new article();
            arti.setImg(list.get(i));
            arti.setWord(word.get(i));
            list2.add(arti);
        }
        displayFoodImgVO.setOther(list2);
        return ServerResponse.createBySuccess("成功获取数据", displayFoodImgVO);
    }

    @RequestMapping("getDiscussFoodImg")
    public ServerResponse test4(){
        List<DiscussFoodImg> list = discussFoodImgService.getDiscussFoodImg();
        return ServerResponse.createBySuccess("成功获取数据", list);
    }


    @RequestMapping("getDiscussFoodImgInfo")
    public ServerResponse test5(@RequestParam("id") int id){
        DiscussFoodImg img = discussFoodImgService.getDiscussFoodImgInfo(id);

        DiscussFoodImgVO discussFoodImgVO =new DiscussFoodImgVO();
        discussFoodImgVO.setId(id);
        discussFoodImgVO.setTitle(img.getTitle());
        List<String> list1 = Arrays.asList(img.getOtherpic().split(";"));
        List<String> list2 = Arrays.asList(img.getOtherword().split(";"));
        List<article> list3 = new LinkedList<article>();
        for(int i = 0; i < list1.size(); ++i){
            article ar = new article();
            ar.setImg(list1.get(i));
            ar.setWord(list2.get(i));
            list3.add(ar);

        }
        discussFoodImgVO.setOther(list3);
        return ServerResponse.createBySuccess("成功获取数据", discussFoodImgVO);
    }

    @RequestMapping("getSearchFoodImg")
    public ServerResponse test6(@RequestParam("cityId") int cityId){
        List<SearchFoodImg> list = searchFoodImgService.getSearchFoodImg(cityId);
        return ServerResponse.createBySuccess("成功获取数据", list);
    }


    @RequestMapping("getUserFood")
    public ServerResponse test7(@RequestParam("cityId") int cityId, @RequestParam("userId") int userId){
        String s = userService.getCollectFood(userId);
        s = s.replace(" ","");
        List<Integer> list2 = new LinkedList<Integer>();
        if(s == null || s.length() == 0){
            return ServerResponse.createBySuccess("成功获取数据",list2);
        }
        List<SearchFoodImg> list = searchFoodImgService.getSearchFoodImg(cityId);
        System.out.println("-------!---");
        for(SearchFoodImg sss: list){
            System.out.println(sss.getSiteId());
        }
        List<String> list1 = Arrays.asList(s.split(","));
        System.out.println("------!---");
        System.out.println(list1);

        for(SearchFoodImg ss: list){

            int flag = 0;
            for(String s2: list1){
                System.out.println(Integer.parseInt(s2));
                if( Integer.parseInt(s2) == (ss.getSiteId())){
                    flag = 1;
                    break;
                }
            }
            if(flag ==  1){
                list2.add(ss.getSiteId());
            }
        }
        return ServerResponse.createBySuccess("成功获取数据", list2);
    }
}
