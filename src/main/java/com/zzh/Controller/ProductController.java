package com.zzh.Controller;

import com.zzh.entity.Product;
import com.zzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<Product> selectAll(@RequestParam(required = false,defaultValue = "1") int pageIndex, @RequestParam(required = false,defaultValue = "5") int pageSize){
        return   productService.selectAll(pageIndex,pageSize);
    }
}
