package com.javapandeng.controller;


import com.javapandeng.base.BaseController;
import com.javapandeng.po.News;
import com.javapandeng.service.NewsService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
    @Autowired
    private NewsService newsService;
    @RequestMapping("findBySql")
    public String findBySql(Model model , News news){
        String sql="select *from news where 1=1 ";
        if(!isEmpty(news.getName())){
            sql += " and name like '%"+news.getName()+"%'";
        }
        sql+=" order by id desc";
        Pager<News> pagers = newsService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",news);
        return "news/news";
    }
    @RequestMapping("/add")
    public String add(){
        return "news/add";
    }
    @RequestMapping("/exAdd")
    public String exAdd(News news){
        news.setAddTime(new Date());
        newsService.insert(news);
        return "redirect:/news/findBySql";
    }

    @RequestMapping("/update")
    public String update(Integer id,Model model){
        News obj=newsService.load(id);  //根据前台传来的id找到该对象
        model.addAttribute("obj",obj);
        return "news/update";
    }
    @RequestMapping("/exUpdate")
    public String exUpdate(){
       newsService.updateById(news);
    }


}
