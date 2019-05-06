package com.zzh.controller;

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
            List<Parameter> parameters = parameterService.selectByVal(parameter, null, null);//拿到所有同样接口的参数
            for (Parameter parameter1 : parameters) {
                //31是主板类别编号
                if (parameter1.getDetails().getCategoryId() == 31) {
                    zhubanList.add(parameter1.getDetails().getProduct());
                }
                //cpu编号33
                else if (parameter1.getDetails().getCategoryId() == 33) {
                    cpuList.add(parameter1.getDetails().getProduct());
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
        //计算功率开始：
        // 如果显卡集合有数据
        if (xiankaList.size() > 0) {
            Parameter parameters = setCondition(xiankaList.get(0), "功耗");
            power += parameters.getParameterInt();
        } else {
            xiankaList.add(product);
        }
        map.addAttribute("xianka", xiankaList.get(0));
        //如果CPU集合有数据
        if (cpuList.size() > 0) {
            Parameter parameters = setCondition(cpuList.get(0), "功耗");
            power += parameters.getParameterInt();
        } else {
            cpuList.add(product);
        }
        map.addAttribute("cpu", cpuList.get(0));
        if (zhubanList.size() == 0) {
            zhubanList.add(product);
        }
        map.addAttribute("zhuban", zhubanList.get(0));
       /* if (neicunList.size()==0) {
            neicunList.add(product);
        }*/
        map.addAttribute("neicun", neicunList.get(0));
        Details details = new Details();
        details.setProduct(product);
        Parameter parameter = new Parameter();
        parameter.setDetails(details);
        parameter.setParameterName("功耗");
        Parameter parameters = parameterService.selectPower(parameter);
        if (parameters == null) {
            power = power + 200;
        } else {
            power = power + 200 + parameters.getParameterInt();
        }
        Details dianyuan = new Details();
        dianyuan.setCategoryId(35);
        Parameter dianyuanP = new Parameter();
        dianyuanP.setDetails(dianyuan);
        List<Parameter> dianyuanList = parameterService.selectByVal(dianyuanP, power - 99, power);
        map.addAttribute("dianyuan", dianyuanList.get(0).getDetails().getProduct());
        map.addAttribute("power", power.toString());
        /*hashmap.put("xianka", xiankaList);
        hashmap.put("cpu", cpuList);
        hashmap.put("zhuban", zhubanList);
        hashmap.put("necun", neicunList);*/
        return "commend2";
    }

    public Parameter setCondition(Product p, String parameterName) {
        Parameter condition = new Parameter();
        condition.setParameterName(parameterName);
        Details condition2 = new Details();
        Product product = new Product();
        product.setProductId(p.getProductId());
        condition2.setProduct(product);
        condition.setDetails(condition2);
        Parameter parameters = parameterService.selectPower(condition);
        return parameters;
    }


    @ResponseBody
    @RequestMapping("selectByPerformance")
    public Map selectByPerformance(double performance, Integer demand) {
        Map map = new HashMap();
        double a = 0.0;
        double b = 0.0;
        double xiankaP = 0;
        double cpuP = 0;
        //内存最高价
        double neicunMoney = performance * 60;
        if (neicunMoney < 300) {
            neicunMoney = 300;
        }
        if (demand == 1) {
            a = 0.25;
            b = 0.75;
        } else if (demand == 2) {
            a = 0.38;
            b = 0.62;
        } else if (demand == 3) {
            a = 0.59;
            b = 0.41;
        } else if (demand == 4) {
            a = 0.67;
            b = 0.33;
        }
        xiankaP = a * performance;
        cpuP = b * performance;
        Product xianka = new Product();
        Category xiankaC = new Category();
        xiankaC.setCategoryName("显卡");
        xianka.setCategory(xiankaC);
        xianka.setPerformance(xiankaP);
        List<Product> xiankaList = productService.selectByCondition(xianka);
        Product cpu = new Product();
        Category cpuC = new Category();
        cpuC.setCategoryName("cpu");
        cpu.setCategory(cpuC);
        cpu.setPerformance(cpuP);
        List<Product> cpuList = productService.selectByCondition(cpu);
        List<Product> zhubanList = new ArrayList<>();
        //方案集合
        List<Map> computerList = new ArrayList<>();
        //拿取5个推荐值最高的,符合要求的cpu
        int count = cpuList.size();
        if (count > 5) {
            count = 5;
        }
        for (int i = 0; i < count; i++) {
            //定义功率初值
            int power = 200;
            //拿出一个CPU
            Product cpuX = cpuList.get(i);
            //获取这个cpu的接口信息
            Parameter parameter = parameterService.selectDetails(cpuX.getDetails().getDetailsId(), "接口");
            //拿到所有同接口的商品
            Parameter sort = new Parameter();
            sort.setParameterVal(parameter.getParameterVal());
            List<Parameter> parameters = parameterService.selectByVal(sort, null, null);
            for (Parameter zhuban : parameters) {
                if (zhuban.getDetails().getCategoryId() == 31) {
                    //获取匹配该接口的所有主板
                    zhubanList.add(zhuban.getDetails().getProduct());
                }
            }
            //生成一个方案
            Map computer = new HashMap();
            //方案中存入一个cpu
            computer.put("cpu", cpuX);
            //获取cpu功率和价格
            double cpuPrice = cpuX.getNewPrice();
            Parameter cpupower = setCondition(cpuX, "功耗");
            power += cpupower.getParameterInt();
            //方案中存入荐值最高的主板，和其价格
            computer.put("zhuban", zhubanList.get(0));
            Double zhubanPrice = zhubanList.get(0).getNewPrice();
            //方案中存储一个显卡(每次存的都不一样)
            double xiankaPrice = 0;
            Parameter xiankapower = null;
            if (xiankaList.size() != 0) {
                computer.put("xianka", xiankaList.get(0));
                xiankaPrice = xiankaList.get(0).getNewPrice();
                xiankapower = setCondition(xiankaList.get(0), "功耗");
                power += xiankapower.getParameterInt();
            }
            //获取显卡的功耗和价格
            //获取内存和其价格
            Product neicunCondition = new Product();
            Category neicunc = new Category();
            neicunc.setCategoryName("内存");
            neicunCondition.setCategory(neicunc);
            neicunCondition.setNewPrice(neicunMoney);
            List<Product> neicunList = productService.selectByCondition(neicunCondition);
            computer.put("neicun", neicunList.get(0));
            double neicunPrice = neicunList.get(0).getNewPrice();
            //获取硬盘，和其价格
            double yingpanPrice = 300;
            //获取电源,和其价格
            Details dianyuan = new Details();
            dianyuan.setCategoryId(35);
            Parameter dianyuanP = new Parameter();
            dianyuanP.setDetails(dianyuan);
            if (power < 400) {
                power = 400;
            }
            List<Parameter> dianyuanList = parameterService.selectByVal(dianyuanP, power - 100, power);
            computer.put("dianyuan", dianyuanList.get(0).getDetails().getProduct());
            double dianyuanPrice = dianyuanList.get(0).getDetails().getProduct().getNewPrice();
            //总价：
            double total = cpuPrice + xiankaPrice + zhubanPrice + neicunPrice + dianyuanPrice + yingpanPrice;
            computer.put("totalPrice", total);
            computerList.add(computer);
        }
        map.put("computerList", computerList);
        System.out.println(map);
        return map;
    }
}
