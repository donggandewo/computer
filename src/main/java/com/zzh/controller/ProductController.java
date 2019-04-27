package com.zzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzh.entity.*;
import com.zzh.service.DetailsService;
import com.zzh.service.ParameterService;
import com.zzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;


import java.util.*;

@Controller
@RequestMapping("product")
public class ProductController {
    private Jedis jedis = new Jedis("localhost");
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

    //@ResponseBody
    @RequestMapping("selectByDetails")
    public String selectByDetails(Integer productId, ModelMap map, String condition, @RequestParam(required = false, defaultValue = "1") Integer userId) {
        List<Product> zhubanList = new ArrayList<>();
        List<Product> cpuList = new ArrayList<>();
        List<Product> neicunList = new ArrayList<>();
        Map hashmap = new HashMap();
        Product product = productService.selectOne(productId);//从前端拿到商品id，通过id拿到商品详情
        Category category = product.getCategory();//通过商品拿到种类
        String val = null;
        //显卡获取
        List<Product> xiankaList = new ArrayList<>();
        //如果没选择显卡 ，获取显卡集合，照旧
        if (category.getCategoryId() != 32) {
            Product xianka = new Product();
            Category xiankac = new Category();
            xiankac.setCategoryName("显卡");
            xianka.setCategory(xiankac);
            xiankaList = productService.selectByCondition(xianka);
            int categoryId = category.getCategoryId();//拿到种类id
            /*map.addAttribute("product",product);*/
            Details detail = detailsService.selectByProduct(productId);//通过商品id拿到详情id
            int detailsId = detail.getDetailsId();
            Parameter parameter = parameterService.selectDetails(detailsId, condition);//通过详情id和接口拿到接口信息
            if (val == null) {
                val = parameter.getParameterVal();
            }
            List<Parameter> parameters = parameterService.selectByVal(val);//拿到所有同样接口的参数
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
            //显卡
        }
        //如果选择显卡，默认推荐i7
        else {
            cpuList.add(productService.selectOne(5));
            zhubanList.add(productService.selectOne(6));
            neicunList.add(productService.selectOne(8));

        }
        Integer power = 0;
        if (xiankaList.size() > 0) {
            map.addAttribute("xianka", xiankaList.get(0));
            Parameter parameters = parameterService.selectPower(xiankaList.get(0).getProductId());
            power += parameters.getParameterInt();
        }
        if (cpuList.size() > 0) {
            map.addAttribute("cpu", cpuList.get(0));
            Parameter parameters = parameterService.selectPower(cpuList.get(0).getProductId());
            power += parameters.getParameterInt();
        }
        if (zhubanList.size() > 0) {
            map.addAttribute("zhuban", zhubanList.get(0));
        }
        if (neicunList.size() > 0) {
            map.addAttribute("neicun", neicunList.get(0));
        }
        Parameter parameters = parameterService.selectPower(productId);
        power = power + 150 + parameters.getParameterInt();
        map.addAttribute("power", power.toString());
        /*hashmap.put("xianka", xiankaList);
        hashmap.put("cpu", cpuList);
        hashmap.put("zhuban", zhubanList);
        hashmap.put("necun", neicunList);*/
        return "commend2";
    }
}
