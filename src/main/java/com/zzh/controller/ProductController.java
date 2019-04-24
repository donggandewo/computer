package com.zzh.controller;

import com.zzh.entity.Category;
import com.zzh.entity.Details;
import com.zzh.entity.Parameter;
import com.zzh.entity.Product;
import com.zzh.service.DetailsService;
import com.zzh.service.ParameterService;
import com.zzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    DetailsService detailsService;
    @Autowired
    ProductService productService;
    @Autowired
    ParameterService parameterService;
    @RequestMapping("selectAll")
    public String selectAll(ModelMap map,@RequestParam(required = false,defaultValue = "1") int pageIndex, @RequestParam(required = false,defaultValue = "5") int pageSize){
        List<Product> list=productService.selectAll(pageIndex,pageSize);
        map.addAttribute("list",list);
        return "product";
    }

    @RequestMapping("selectByCondition")
    public String selectByCondition(Product product, ModelMap map) {
        List<Product> list = productService.selectByCondition(product);
        map.addAttribute("list", list);
        return "liebiao";
    }

    @ResponseBody
    @RequestMapping("selectByDetails")
    public Object selectByDetails(int productId, ModelMap map, String condition) {
        Map hashmap = new HashMap();
        Product product = productService.selectOne(productId);
        Category category = product.getCategory();
        int categoryId = category.getCategoryId();
        /*map.addAttribute("product",product);*/
        Details detail = detailsService.selectByProduct(productId);
        int detailsId = detail.getDetailsId();
        Parameter parameter = parameterService.selectDetails(detailsId, condition);
        String val = parameter.getParameterVal();
        List<Parameter> parameters = parameterService.selectByVal(val);
        List<Product> zhubanList = new ArrayList<>();
        List<Product> cpuList = new ArrayList<>();
        for (Parameter parameter1 : parameters) {
            Details details = parameter1.getDetails();
            int productId1 = details.getProductId();
            Product productx = productService.selectOne(productId1);
            if (productx.getCategory().getCategoryId() != categoryId) {
                if (productx.getCategory().getCategoryName().equals("主板")) {
                    zhubanList.add(productx);
                } else if (productx.getCategory().getCategoryName().equals("cpu")) {
                    cpuList.add(productx);
                }
            }
        }
        hashmap.put("cpu", cpuList);
        hashmap.put("zhuban", zhubanList);
        return hashmap;
    }
}
