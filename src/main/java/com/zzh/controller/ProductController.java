package com.zzh.controller;

import com.zzh.entity.Product;
import com.zzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping("selectAll")
    @ResponseBody
    public String selectAll(ModelMap map,@RequestParam(required = false,defaultValue = "1") int pageIndex, @RequestParam(required = false,defaultValue = "5") int pageSize){
        List<Product> list=productService.selectAll(pageIndex,pageSize);
        map.addAttribute("list",list);
        return "product";
    }
}
