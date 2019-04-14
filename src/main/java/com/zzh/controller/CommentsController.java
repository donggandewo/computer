package com.zzh.controller;

import com.zzh.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @RequestMapping("selectByProduct")
    public String selectByProduct(ModelMap map,int productId){
        commentsService.selectByProduct(productId);
        return "comments";
    }
}
