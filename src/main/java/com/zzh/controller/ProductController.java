package com.zzh.controller;

import com.zzh.entity.*;
import com.zzh.service.DetailsService;
import com.zzh.service.ParameterService;
import com.zzh.service.ProductService;
import com.zzh.util.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;


import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public String selectByDetails(Integer productId, ModelMap map, String condition, @RequestParam(required = false, defaultValue = "1") Integer userId, HttpSession session) {
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
        List<Parameter> dianyuanList = parameterService.selectByVal(dianyuanP, power - 99, power + 50);
        map.addAttribute("dianyuan", dianyuanList.get(0).getDetails().getProduct());
        map.addAttribute("power", power.toString());
        Product yingpan = new Product();
        yingpan.setProductName("256GB固态硬盘");
        yingpan.setOldPrice(320.0);
        yingpan.setNewPrice(300.0);
        map.addAttribute("yingpan", yingpan);
        map.addAttribute("totalPrice", cpuList.get(0).getNewPrice() + zhubanList.get(0).getNewPrice() + xiankaList.get(0).getNewPrice() + neicunList.get(0).getNewPrice() + dianyuanList.get(0).getDetails().getProduct().getNewPrice() + yingpan.getNewPrice());
        session.setAttribute("cpu", cpuList.get(0));
        session.setAttribute("zhuban", zhubanList.get(0));
        session.setAttribute("xianka", xiankaList.get(0));
        session.setAttribute("neicun", neicunList.get(0));
        session.setAttribute("yingpan", yingpan);
        session.setAttribute("dianyuan", dianyuanList.get(0));
        session.setAttribute("totalPrice", cpuList.get(0).getNewPrice() + zhubanList.get(0).getNewPrice() + xiankaList.get(0).getNewPrice() + neicunList.get(0).getNewPrice() + dianyuanList.get(0).getDetails().getProduct().getNewPrice() + yingpan.getNewPrice());
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


    @RequestMapping("selectByPerformance")
    public String selectByPerformance(double performance, Integer demand, ModelMap modelMap, HttpSession session) {
        Map map = new HashMap();
        double a = 0.0;
        double b = 0.0;
        double xiankaPX = 0;
        double xiankaPN = 0;
        double cpuPX = 0;
        double cpuPN = 0;
        //内存最高价
        double neicunMoney = performance * 20;
        //内存最低价
        if (neicunMoney < 220) {
            neicunMoney = 220;
        }
        if (demand == 1) {
            a = 0.25;
            b = 0.75;
        } else if (demand == 2) {
            a = 0.38;
            b = 0.62;
        } else if (demand == 3) {
            a = 0.58;
            b = 0.42;
        } else if (demand == 4) {
            a = 0.67;
            b = 0.33;
        }
        //显卡和cpu上限与下限
        xiankaPX = a * performance;
        xiankaPN = (performance - 7) * a;
        cpuPX = b * performance;
        cpuPN = (performance - 7) * b;
        Product xianka = new Product();
        Category xiankaC = new Category();
        xiankaC.setCategoryName("显卡");
        xianka.setCategory(xiankaC);
        List<Product> xiankaList = productService.selectByCondition(xianka, xiankaPX, xiankaPN);
        Product cpu = new Product();
        Category cpuC = new Category();
        cpuC.setCategoryName("cpu");
        cpu.setCategory(cpuC);
        List<Product> cpuList = productService.selectByCondition(cpu, cpuPX, cpuPN);
        List<Product> zhubanList = new ArrayList<>();
        //方案集合
        List<Map> computerList = new ArrayList<>();
        //拿取5个推荐值最高的,符合要求的cpu
        int count = cpuList.size();
        if (count > 5) {
            count = 5;
        }
        loop1:
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
            //方案中存储一个显卡
            double xiankaPrice = 0;
            Parameter xiankapower = null;
            computer.put("xianka", null);
            if (xiankaList.size() != 0) {
                computer.put("xianka", xiankaList.get(0));
                xiankaPrice = xiankaList.get(0).getNewPrice();
                xiankapower = setCondition(xiankaList.get(0), "功耗");
                power += xiankapower.getParameterInt();
            }
            //如果没有显卡 需要判断CPU是否含有核显
            else {
                Parameter hexian = parameterService.selectDetails(cpuX.getDetails().getDetailsId(), "有无核显");
                //如果没有作废方案,清空之前存储的数据
                if (hexian.getParameterInt() == 0) {
                    computer.clear();
                    zhubanList.clear();
                    xiankaList.clear();
                    continue loop1;
                }
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
            Product yingpan = new Product();
            yingpan.setProductName("256GB固态硬盘");
            yingpan.setOldPrice(320.0);
            yingpan.setProductId(17);
            double yingpanPrice = 300.0;
            yingpan.setNewPrice(yingpanPrice);
            computer.put("yingpan", yingpan);
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


        //所有推荐方案
        map.put("computerList", computerList);


        Map fangan = computerList.get(0);
        modelMap.addAttribute("list", computerList);
        modelMap.addAttribute("cpu", fangan.get("cpu"));
        modelMap.addAttribute("zhuban", fangan.get("zhuban"));
        modelMap.addAttribute("xianka", fangan.get("xianka"));
        modelMap.addAttribute("neicun", fangan.get("neicun"));
        modelMap.addAttribute("yingpan", fangan.get("yingpan"));
        modelMap.addAttribute("dianyuan", fangan.get("dianyuan"));
        modelMap.addAttribute("totalPrice", fangan.get("totalPrice"));
        session.setAttribute("cpu", fangan.get("cpu"));
        session.setAttribute("zhuban", fangan.get("zhuban"));
        session.setAttribute("xianka", fangan.get("xianka"));
        session.setAttribute("neicun", fangan.get("neicun"));
        session.setAttribute("yingpan", fangan.get("yingpan"));
        session.setAttribute("dianyuan", fangan.get("dianyuan"));
        session.setAttribute("totalPrice", fangan.get("totalPrice"));
        session.setAttribute("computerList", computerList);
        return "test2";
        /*return "commend2";*/
        /*return map;*/
    }

    @RequestMapping("insert")
    public String insert(ModelMap map, Product product) {
        product.setAddTime(new Date());
        if (product.getNewPrice() != null &&
                product.getOldPrice() != null &&
                product.getAddTime() != null &&
                product.getMedia() != null &&
                product.getMediaNum() != null &&
                product.getPerformance() != null &&
                product.getSales() != null
        ) {
            double recommend = Recommend.getRecommend(product.getNewPrice(), product.getOldPrice(), product.getPerformance(), product.getMedia(), product.getMediaNum(), product.getSales(), product.getAddTime());
            product.setRecommend(recommend);
        } else if (product.getSales() != null &&
                product.getAddTime() != null &&
                product.getOldPrice() != null &&
                product.getNewPrice() != null) {
            double recommend = Recommend.getRecommend(product.getNewPrice(), product.getOldPrice(), product.getSales(), product.getAddTime());
            product.setRecommend(recommend);
        } else {
            map.addAttribute("warring", "无法获取推荐值，因为参数不全");
        }
        productService.insert(product);
        return "insert";
    }

    //查找一个商品信息
    @RequestMapping("selectOne")
    public String selectOne(int productId, ModelMap map) {
        Product product = productService.selectOne(productId);
        map.addAttribute("product", product);
        return "update";
    }

    //修改
    @RequestMapping("update")
    public String update(Product product, ModelMap map, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
        try {
            Date s1 = sdf.parse(time);
            product.setAddTime(s1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (product.getNewPrice() != null &&
                product.getOldPrice() != null &&
                product.getAddTime() != null &&
                product.getMedia() != null &&
                product.getMediaNum() != null &&
                product.getPerformance() != null &&
                product.getSales() != null
        ) {

            double recommend = Recommend.getRecommend(product.getNewPrice(), product.getOldPrice(), product.getPerformance(), product.getMedia(), product.getMediaNum(), product.getSales(), product.getAddTime());
            product.setRecommend(recommend);
        } else if (product.getSales() != null &&
                product.getAddTime() != null &&
                product.getOldPrice() != null &&
                product.getNewPrice() != null) {
            double recommend = Recommend.getRecommend(product.getNewPrice(), product.getOldPrice(), product.getSales(), product.getAddTime());
            product.setRecommend(recommend);
        } else {
            map.addAttribute("warring", "修改失败,缺少参数");
        }
        productService.update(product);
        return "product";
    }

    @RequestMapping("selectByCondition2")
    public String selectByCondition2(Product product, ModelMap map) {
        //商品集合
        List<Product> list = new ArrayList<>();
        //商品的信息
        Product p = productService.selectOne(product.getProductId());
        Category category = p.getCategory();
        //如果要切换的商品是主板
        if (category.getCategoryId() == 31) {
            //拿到主板的接口信息
            Parameter parameter = parameterService.selectDetails(p.getDetails().getDetailsId(), "接口");
            //拿到所有接口信息一致的商品
            List<Parameter> parameters = parameterService.selectByVal(parameter, null, null);
            for (Parameter parameter1 : parameters) {
                //拿到所有接口信息一致的主板商品
                if (parameter1.getDetails().getCategoryId() == 31) {
                    //存入商品
                    list.add(parameter1.getDetails().getProduct());
                }
            }
        } else if (category.getCategoryId() == 33) {
            Parameter parameter = parameterService.selectDetails(p.getDetails().getDetailsId(), "接口");
            //拿到所有接口信息一致的商品
            List<Parameter> parameters = parameterService.selectByVal(parameter, null, null);
            for (Parameter parameter1 : parameters) {
                //拿到所有接口信息一致的cpu商品
                if (parameter1.getDetails().getCategoryId() == 33) {
                    //存入商品
                    list.add(parameter1.getDetails().getProduct());
                }
            }
        } else if (category.getCategoryId() == 34) {
            Parameter parameter = parameterService.selectDetails(p.getDetails().getDetailsId(), "内存频率");
            //拿到所有内存频率信息一致的商品
            List<Parameter> parameters = parameterService.selectByVal(parameter, null, null);
            for (Parameter parameter1 : parameters) {
                //拿到所有内存频率信息一致的cpu商品
                if (parameter1.getDetails().getCategoryId() == 34) {
                    //存入商品
                    list.add(parameter1.getDetails().getProduct());
                }
            }
        } else {
            list = productService.selectByCondition(product);
        }
        map.addAttribute("list", list);
        return "liebiao2";
    }

    @RequestMapping("change")
    public String change(HttpSession session, ModelMap modelMap, Integer categoryId, int productId) {
        Product product = productService.selectOne(productId);
        Double price = 0.0;
        if (categoryId == 31) {
            Product p = (Product) session.getAttribute("zhuban");
            session.setAttribute("zhuban", product);
            price = (Double) session.getAttribute("totalPrice");

            price = price - p.getNewPrice() + product.getNewPrice();

        } else if (categoryId == 32) {
            Product p = (Product) session.getAttribute("xianka");
            session.setAttribute("xianka", product);
            price = (Double) session.getAttribute("totalPrice");
            price = price - p.getNewPrice() + product.getNewPrice();
        } else if (categoryId == 33) {
            Product p = (Product) session.getAttribute("cpu");
            session.setAttribute("cpu", product);
            price = (Double) session.getAttribute("totalPrice");
            price = price - p.getNewPrice() + product.getNewPrice();
        } else if (categoryId == 34) {
            Product p = (Product) session.getAttribute("neicun");
            session.setAttribute("neicun", product);
            price = (Double) session.getAttribute("totalPrice");
            price = price - p.getNewPrice() + product.getNewPrice();
        } else if (categoryId == 35) {
            Product p = (Product) session.getAttribute("dianyuan");
            session.setAttribute("dianyuan", product);
            price = (Double) session.getAttribute("totalPrice");
            price = price - p.getNewPrice() + product.getNewPrice();
        }
        session.setAttribute("totalPrice", price);
        modelMap.addAttribute("cpu", session.getAttribute("cpu"));
        modelMap.addAttribute("zhuban", session.getAttribute("zhuban"));
        modelMap.addAttribute("xianka", session.getAttribute("xianka"));
        modelMap.addAttribute("neicun", session.getAttribute("neicun"));
        modelMap.addAttribute("yingpan", session.getAttribute("yingpan"));
        modelMap.addAttribute("dianyuan", session.getAttribute("dianyuan"));
        modelMap.addAttribute("totalPrice", session.getAttribute("totalPrice"));
        return "commend2";
    }


    @RequestMapping("getComputer")
    public String getComputer(Double totalPrice, HttpSession session, ModelMap modelMap) {
        List<Map> list = (List<Map>) session.getAttribute("computerList");
        for (Map map : list) {
            Double price = (Double) map.get("totalPrice");
            if (totalPrice.equals(price)) {
                modelMap.addAttribute("cpu", map.get("cpu"));
                modelMap.addAttribute("zhuban", map.get("zhuban"));
                modelMap.addAttribute("xianka", map.get("xianka"));
                modelMap.addAttribute("neicun", map.get("neicun"));
                modelMap.addAttribute("yingpan", map.get("yingpan"));
                modelMap.addAttribute("dianyuan", map.get("dianyuan"));
                modelMap.addAttribute("totalPrice", map.get("totalPrice"));
                session.setAttribute("totalPrice", map.get("totalPrice"));
            }
        }
        return "commend2";
    }

    @RequestMapping("select")
    public String select(int productId, ModelMap map) {
        Product product = productService.selectOne(productId);
        map.addAttribute("product", product);
        return "xiangqing";
    }
}
