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
    public String selectAll(ModelMap map, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "5") int pageSize) {
        List<Product> list = productService.selectAll(pageIndex, pageSize);
        map.addAttribute("list", list);
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
        Product product = productService.selectOne(productId);//从前端拿到商品id，通过id拿到商品详情
        Category category = product.getCategory();//通过商品拿到种类
        int categoryId = category.getCategoryId();//拿到种类id
        /*map.addAttribute("product",product);*/
        Details detail = detailsService.selectByProduct(productId);//通过商品id拿到详情id
        int detailsId = detail.getDetailsId();
        Parameter parameter = parameterService.selectDetails(detailsId, condition);//通过详情id和接口拿到接口信息
        String val = parameter.getParameterVal();
        List<Parameter> parameters = parameterService.selectByVal(val);//拿到所有同样接口的参数
        List<Product> zhubanList = new ArrayList<>();
        List<Product> cpuList = new ArrayList<>();
        List<Product> neicunList = new ArrayList<>();
        for (Parameter parameter1 : parameters) {
            //如果传过来的商品id与查出来的所有符合条件的商品id不同(需要相同接口但是不同类别的产品)
            if (parameter1.getDetails().getCategoryId() != categoryId) {
                //31是主板类别编号
                if (parameter1.getDetails().getCategoryId() == 31) {
                    zhubanList.add(parameter1.getDetails().getProduct());
                }
                //cpu编号33
                else if (parameter1.getDetails().getCategoryId() == 33) {
                    cpuList.add(parameter1.getDetails().getProduct());
                }
            }
        }
        /*for (Parameter parameter1 : parameters) {
            Details details = parameter1.getDetails();//拿到所有同样接口的参数的商品详情
            int productId1 = details.getProductId();
            Product productx = productService.selectOne(productId1);//拿到所有商品
            if (productx.getCategory().getCategoryId() != categoryId) {
                if (productx.getCategory().getCategoryName().equals("主板")) {
                    zhubanList.add(productx);
                } else if (productx.getCategory().getCategoryName().equals("cpu")) {
                    cpuList.add(productx);
                }
            }
        }*/
        //是否选择主板
        //是且选择的是am4主板
        if (product.getCategory().getCategoryId() == 31 && val.equals("AM4")) {
            List<Parameter> neicun = parameterService.selectByDDR(2600, 3000);
            for (Parameter neicun2 : neicun) {
                //只要cpu
                if (neicun2.getDetails().getCategoryId() == 34) {
                    neicunList.add(neicun2.getDetails().getProduct());
                }
            }
        }
        /*//查内存
        //amd=2,如果商品是amd牌子的话
        if(product.getBrands().getBrandsId()==2){
            //如果是AM4接口
            if(val.equals("AM4")){
                List<Parameter> neicun = parameterService.selectByDDR(2600, 3000);
                for (Parameter neicun2 : neicun) {
                    //只要cpu
                    if(neicun2.getDetails().getCategoryId()==33){
                        neicunList.add(neicun2.getDetails().getProduct());
                    }
                }
            }*/
        //是主板但不是AM4接口，但是amd cpu
        else if (product.getCategory().getCategoryId() == 31 && product.getBrands().getBrandsId() == 2) {
            List<Parameter> neicun = parameterService.selectByDDR(1333, 2000);
            for (Parameter neicun2 : neicun) {
                //只要内存
                if (neicun2.getDetails().getCategoryId() == 34) {
                    neicunList.add(neicun2.getDetails().getProduct());
                }
            }
        }
        //不是主板 但是amdcpu
        else if (product.getBrands().getBrandsId() == 2) {
            //如果是AM4接口
            if (val.equals("AM4")) {
                List<Parameter> neicun = parameterService.selectByDDR(2600, 3000);
                for (Parameter neicun2 : neicun) {
                    //只要内存
                    if (neicun2.getDetails().getCategoryId() == 34) {
                        neicunList.add(neicun2.getDetails().getProduct());
                    }
                }
            }
            //不是am4接口
            else {
                List<Parameter> neicun = parameterService.selectByDDR(1300, 2000);
                for (Parameter neicun2 : neicun) {
                    //只要cpu
                    if (neicun2.getDetails().getCategoryId() == 34) {
                        neicunList.add(neicun2.getDetails().getProduct());
                    }
                }
            }
        }
        //是主板 但不是amd主板,是英特尔主板（此时内存无所谓）
        //或者是英特尔处理器（无所谓）
        else {
            List<Parameter> neicun = parameterService.selectByDDR(1333, 3000);
            for (Parameter neicun2 : neicun) {
                //只要内存
                if (neicun2.getDetails().getCategoryId() == 34) {
                    neicunList.add(neicun2.getDetails().getProduct());
                }
            }
        }

        hashmap.put("cpu", cpuList);
        hashmap.put("zhuban", zhubanList);
        hashmap.put("necun", neicunList);
        return hashmap;
    }
}
