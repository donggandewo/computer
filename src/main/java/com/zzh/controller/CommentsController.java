package com.zzh.controller;

import com.zzh.entity.Comment;
import com.zzh.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @RequestMapping("selectByProduct")
    public String selectByProduct(ModelMap map,int productId){
        List<Comment> comments = commentsService.selectByProduct(productId);
        map.addAttribute("comments", comments);
        return "comments";
    }
}
