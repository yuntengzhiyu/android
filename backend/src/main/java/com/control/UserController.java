package com.control;


import com.pojo.*;
import com.service.SearchFoodImgService;
import com.service.SearchImgService;
import com.service.UserService;
import com.util.ServerResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@ResponseBody
public class UserController {


    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("searchImgServiceImpl")
    private SearchImgService searchImgService;

    @Autowired
    @Qualifier("searchFoodImgServiceImpl")
    private SearchFoodImgService searchFoodImgService;

    @RequestMapping("getUsername")
    public ServerResponse test( String username){
        System.out.println(username);
        User user = userService.getUsername(username);
        if(user == null)
            return ServerResponse.createBySuccess("没有该用户名",null);
        else
            return ServerResponse.createByError("已经有该用户名了");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse test2(String username, String password){
        System.out.println(username);
        int num = userService.rigister(username, password);
        if(num > 0){
            return ServerResponse.createBySuccess("成功插入数据",null);
        }
        else{
            return ServerResponse.createByError("插入失败");
        }

    }

    @RequestMapping("login")
    public ServerResponse test3(String username, String password){
        User user = userService.login(username, password);
        if(user == null){
            return ServerResponse.createByError("没有这样的用户");
        }
        else return ServerResponse.createBySuccess("登录成功",user);
    }


    @RequestMapping("addsite")
    public ServerResponse test4( @RequestParam("userId") int userId ,@RequestParam("siteId") int siteId1){
        String siteId =  String.valueOf(siteId1) + ',';
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("siteId",siteId);
        System.out.println(userId);
        System.out.println(siteId);

        int num = userService.Addsite(map);
        if(num > 0){
            return ServerResponse.createBySuccess("成功添加",null);
        }
        else{
            return ServerResponse.createByError("添加失败");
        }
    }


    @RequestMapping("havesite")
    public ServerResponse test5(@RequestParam("userId") int userId, @RequestParam("siteId") int siteId){
//            System.out.println(userId);
//            System.out.println(siteId);
            String s = userService.getCollect(userId);

//            System.out.println(s);
            if( s==null || s.equals("") || s.length() == 0){
//                System.out.println("ha");
                return ServerResponse.createByError("还没有这个景点");
            }
            List<String> list = Arrays.asList(s.split(","));
//            System.out.println(list);
            int flag = 0;
            for(int i = 0; i < list.size(); ++i){
                if(list.get(i).equals(String.valueOf(siteId))){
                    flag = 1;
                    break;
                }
            }
            if(flag > 0) return ServerResponse.createBySuccess("已经添加这个景点了",null);
            else return ServerResponse.createByError("还没有添加这个景点");

    }

    @RequestMapping(value = "deletesite")
    public ServerResponse test6(@RequestParam("userId") int userId, @RequestParam("siteId") int siteId){
        String s = userService.getCollect(userId);
        System.out.println(s);
        s = s.replace(" ","");
        System.out.println(s);
//        System.out.println(s);
        List<String> list = Arrays.asList(s.split(","));
//        System.out.println(list);
        List<String> list1 = new LinkedList<String>();
        for(int i = 0; i< list.size(); ++i){
            if(list.get(i).equals(String.valueOf(siteId))){
                continue;
            }
            list1.add(list.get(i));
        }

        String s1 = list1.toString();
        s1 = s1.replace(" ","");
        s1 = s1.replace("[","");
        s1 = s1.replace("]","");
        int num = userService.setCollect(userId, s1);
        if(num > 0){
            return ServerResponse.createBySuccess("成功删除数据", null);
        }
        else return ServerResponse.createByError("删除失败");

    }

    @RequestMapping("findUsername")
    public ServerResponse test7(@RequestParam("userId") int userId){
        String s = userService.findUsername(userId);

        return ServerResponse.createBySuccess("用户名获取成功", s);
    }


    @RequestMapping("getSiteList")
    public ServerResponse test8(@RequestParam("userId") int userId){
        System.out.println(userId);
        String s = userService.getCollect(userId);
        System.out.println(s);
        s = s.replace(" ","");
        System.out.println(s);
        if(s == null || s.length() == 0){
            return ServerResponse.createByError("什么都没有哦");
        }
        List<String>list = Arrays.asList(s.split(","));
        List<Collectsite>theList = new LinkedList<Collectsite>();
        for(String s1: list){
            Collectsite searchImg = searchImgService.getCollectSiteById(Integer.parseInt(s1));
            searchImg.setSitePic(searchImg.getSitePic().split(",")[0]);
            theList.add(searchImg);
        }
        return ServerResponse.createBySuccess("成功获取数据", theList);
    }


    @RequestMapping("havefood")
    public ServerResponse test9(@RequestParam("userId") int userId, @RequestParam("siteId") int siteId){
//            System.out.println(userId);
//            System.out.println(siteId);
        String s = userService.getCollectFood(userId);
       // s = s.replace(" ","");
//            System.out.println(s);
        if( s==null || s.equals("") || s.length() == 0){
//                System.out.println("ha");
            return ServerResponse.createByError("还没有这个美食");
        }
        List<String> list = Arrays.asList(s.split(","));
//            System.out.println(list);
        int flag = 0;
        for(int i = 0; i < list.size(); ++i){
            if(list.get(i).equals(String.valueOf(siteId))){
                flag = 1;
                break;
            }
        }
        if(flag > 0) return ServerResponse.createBySuccess("已经添加这个美食了",null);
        else return ServerResponse.createByError("还没有添加这个美食");

    }


    @RequestMapping(value = "deletefood")
    public ServerResponse test10(@RequestParam("userId") int userId, @RequestParam("siteId") int siteId){
        String s = userService.getCollectFood(userId);
        s = s.replace(" ","");
//        System.out.println(s);
        List<String> list = Arrays.asList(s.split(","));
//        System.out.println(list);
        List<String> list1 = new LinkedList<String>();
        for(int i = 0; i< list.size(); ++i){
            if(list.get(i).equals(String.valueOf(siteId))){
                continue;
            }
            list1.add(list.get(i));
        }

        String s1 = list1.toString();

        s1 = s1.replace("[","");
        s1 = s1.replace("]","");
        int num = userService.setCollectFood(userId, s1);
        if(num > 0){
            return ServerResponse.createBySuccess("成功删除数据", null);
        }
        else return ServerResponse.createByError("删除失败");

    }


    @RequestMapping("addfood")
    public ServerResponse test11( @RequestParam("userId") int userId ,@RequestParam("siteId") int siteId1){
        String siteId =  String.valueOf(siteId1) + ',';
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("siteId",siteId);
        System.out.println(userId);
        System.out.println(siteId);

        int num = userService.Addfood(map);
        if(num > 0){
            return ServerResponse.createBySuccess("成功添加",null);
        }
        else{
            return ServerResponse.createByError("添加失败");
        }
    }


    @RequestMapping("getFoodList")
    public ServerResponse test12(@RequestParam("userId") int userId){

        String s = userService.getCollectFood(userId);
        s = s.replace(" ","");
        if(s == null || s.length() == 0){
            return ServerResponse.createByError("什么都没有");
        }
        List<String>list = Arrays.asList(s.split(","));
        List<SearchFoodImg>theList = new LinkedList<SearchFoodImg>();
        for(String s1: list){
            SearchFoodImg searchFoodImg = searchFoodImgService.getCollectFoodById(Integer.parseInt(s1));
            theList.add(searchFoodImg);
        }
        return ServerResponse.createBySuccess("成功获取数据", theList);
    }





}
