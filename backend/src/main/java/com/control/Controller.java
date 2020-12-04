package com.control;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.fabric.Server;
import com.pojo.*;
import com.service.*;
import com.util.ServerResponse;
//import org.graalvm.compiler.nodes.calc.IntegerTestNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController

public class Controller{


    @Autowired
    @Qualifier("swiperServiceImpl")
    private SwiperService swiperService;

    @Autowired
    @Qualifier("displayImgServiceImpl")
    private DisplayImgService displayImgService;

    @Autowired
    @Qualifier("discussImgServiceImpl")
    private DiscussImgService discussImgService;

    @Autowired
    @Qualifier("searchImgServiceImpl")
    private SearchImgService searchImgService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

//    @RequestMapping("/getimg")
//    public ServerResponse test6(){
//        Map<String ,Object> map = new HashMap<String, Object>();
//        List<Img2> img2 = imgService.getImg2();
//        List<Img2VO> theList = new LinkedList<Img2VO>();
//        for(Img2 x : img2){
//            Img2VO k = Img2VOconverter.convert(x);
//            theList.add(k);
//        }
//        map.put("img", theList) ;
//        return ServerResponse.createBySuccess("成功",map);
//    }

    @RequestMapping("getdataimg")
    public ServerResponse getdataimg(@RequestParam("cityId") int cityId){
        List<SearchImg> list = searchImgService.getSearchImg(cityId);
        Map<String,Object>map = new HashMap<String, Object>();
        List<String> siteName = new LinkedList<String>();
        List<Double> points = new LinkedList<Double>();
        List<Map<String,Object>> data = new LinkedList<Map<String, Object>>();

        for(SearchImg searchImg : list){
            String s = searchImg.getSiteName();
            siteName.add(s);
            Double num = Double.parseDouble(searchImg.getCommentsPoint().substring(0,3));
            points.add(num);
            Map<String,Object> map1 = new HashMap<String, Object>();
            map1.put("name",s);
//            String ss = searchImg.getCommentsNum().substring(0,)
            int num1 = Integer.parseInt(searchImg.getCommentsNum().substring(0, searchImg.getCommentsNum().length() - 3));
            map1.put("data",num1);
            data.add(map1);
        }
        map.put("siteName",siteName);
        map.put("points",points);
        map.put("data",data);
        return ServerResponse.createBySuccess("成功获取数据",map);
    }

    @RequestMapping("getSwiper")
    public ServerResponse test(){
        Map<String ,Object> map = new HashMap<String, Object>();
        List<swiper> list = swiperService.getSwiper();
        return ServerResponse.createBySuccess("成功获取轮播图",list);
    }


    @RequestMapping("getDisplayImg")
    public ServerResponse test1(){
        List<DisplayImg> list = displayImgService.getDisplayImg();
        return ServerResponse.createBySuccess("成功获取图片", list);
    }


    @RequestMapping("getDisplayImgInfo/{id}")
    public ServerResponse test2(@PathVariable("id") int id){
        List<DisplayImgInfo> theList = new LinkedList<DisplayImgInfo>();
        DisplayImg img = displayImgService.getInfo(id);
        List<String> list1 = Arrays.asList(img.getOtherTitle().split(","));
        List<String> list2 = Arrays.asList(img.getOtherImg().split(","));
        List<String> list3 = Arrays.asList(img.getOtherScore().split(","));
        for(int i = 0; i < list1.size(); ++i){
            DisplayImgInfo displayImgInfo = new DisplayImgInfo();
            displayImgInfo.setOtherTitle(list1.get(i));
            displayImgInfo.setOtherImg(list2.get(i));
            displayImgInfo.setOtherScore(list3.get(i));
            theList.add(displayImgInfo);
        }

        return ServerResponse.createBySuccess("成功获取数据",theList);
    }

    @RequestMapping("getDiscussImg")
    public ServerResponse test3(){
        List<DiscussImg> list = discussImgService.getDiscussImg();
        return ServerResponse.createBySuccess("成功获取数据",list);
    }

    @RequestMapping("getDiscussImgInfo/{id}")
    public ServerResponse test4(@PathVariable("id") int id){
        DiscussImg img = discussImgService.getDiscussImgInfo(id);
        return ServerResponse.createBySuccess("成功获取数据", img);
    }

    @RequestMapping("getSearchImg/{cityId}")
    public ServerResponse test5(@PathVariable("cityId") int cityId){
        List<SearchImg> list = searchImgService.getSearchImg(cityId);
        for(SearchImg s : list){
            s.setSitePic(s.getSitePic().split(",")[0]);
        }
        return ServerResponse.createBySuccess("成功",list);
    }

    @RequestMapping("getSearchImgInfo/{id}")
    public ServerResponse test6(@PathVariable("id") int id){
        SearchImg img = searchImgService.getSearchImgInfo(id);

        SearchImgInfo searchImgInfo = new SearchImgInfo();
        List<String>list1 = Arrays.asList(img.getSitePic().split(","));
        List<String>list2 = Arrays.asList(img.getOthersitePic().split(","));
        List<String>list3 = Arrays.asList(img.getOthersiteName().split(","));
        List<String>list4 = Arrays.asList(img.getOthersiteDis().split(","));
        searchImgInfo.setId(id);
        searchImgInfo.setSiteAddress(img.getSiteAddress());
        searchImgInfo.setSiteIntro(img.getSiteIntro());
        searchImgInfo.setSiteName(img.getSiteName());
        searchImgInfo.setSitePhone(img.getSitePhone());
        searchImgInfo.setSitePolicy(img.getSitePolicy());
        searchImgInfo.setSitePic(list1);

        List<othersite> list5 = new LinkedList<othersite>();
        for(int i = 0;  i < list2.size(); ++i){
            othersite othersite1 = new othersite();
            othersite1.setOthersitePic(list2.get(i));
            othersite1.setOthersiteName(list3.get(i));
            othersite1.setOthersiteDis(list4.get(i));
            list5.add(othersite1);
        }
        searchImgInfo.setOthersite(list5);
        return ServerResponse.createBySuccess("成功",searchImgInfo);
    }




    @RequestMapping("getUserSite")
    public ServerResponse test7(@RequestParam("cityId") int cityId, @RequestParam("userId") int userId){
        String s = userService.getCollect(userId);

        List<Integer> list2 = new LinkedList<Integer>();

        if(s == null || s.length() == 0){
            return ServerResponse.createBySuccess("成功获取数据",list2);
        }
        List<SearchImg> list = searchImgService.getSearchImg(cityId);
        System.out.println("-------!---");
        for(SearchImg sss: list){
            System.out.println(sss.getId());
        }
        List<String> list1 = Arrays.asList(s.split(","));
        System.out.println("------!---");
        System.out.println(list1);

        for(SearchImg ss: list){

            int flag = 0;
            for(String s2: list1){
                System.out.println(Integer.parseInt(s2));
                if( Integer.parseInt(s2) == (ss.getId())){
                    flag = 1;
                    break;
                }
            }
            if(flag ==  1){
                list2.add(ss.getId());
            }
        }
        System.out.println("dfs");
        return ServerResponse.createBySuccess("成功获取数据", list2);
    }
}
