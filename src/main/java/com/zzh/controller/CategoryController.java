package com.zzh.Controller;

import com.zzh.entity.Category;
import com.zzh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("selectAll")
    public String selectAll(ModelMap map){
        List<Category> categories = categoryService.selectAll();
        map.addAttribute("categories",categories);
        System.out.println("fuck");
        return "category";
    }
}
