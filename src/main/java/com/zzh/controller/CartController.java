package com.zzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzh.entity.Cart;
import com.zzh.entity.Product;
import com.zzh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private ProductService productService;

    private Jedis jedis = new Jedis("localhost");

    @RequestMapping("addTo")
    public ModelAndView addTo(HttpSession session, Integer productId) {

        Integer userId = (Integer) session.getAttribute("userId");
        Cart cart = JSONObject.parseObject(jedis.get(userId.toString()), Cart.class);
        if (cart == null) {
            cart = new Cart();
        }
        LinkedHashMap<Integer, Product> map = cart.getMap();
        boolean f = map.containsKey(productId);
        Product product;
        if (f) {
            product = map.get(productId);
            if (product.getStatus() == 0) {
                product.setBuyCount(product.getBuyCount() + 1);
            }
        } else {
            map.put(productId, productService.selectOne(productId));
        }
        cart.setMap(map);
        jedis.set(userId.toString(), JSONObject.toJSONString(cart));
        ModelAndView mv = new ModelAndView("redirect:/product/select?productId=" + productId);
        return mv;
    }

    @RequestMapping("cartPrice")
    public String cartPrice(HttpSession session, ModelMap modelMap) {
        Cart cart;
        Integer userId = (int) session.getAttribute("userId");
        cart = JSONObject.parseObject(jedis.get(userId.toString()), Cart.class);
        if (cart == null) {
            cart = new Cart();
        }
        Map<Integer, Product> map = cart.getMap();
        Set<Integer> set = map.keySet();
        double pp = 0;
        double pd = 0;
        for (Integer integer : set) {
            Product product = map.get(integer);
            if (product.getStatus() == 0) {
                pd += product.getBuyCount() * product.getNewPrice();//现价
                pp += product.getOldPrice() * product.getBuyCount();//原价
            }
        }
        cart.setPrice2(pp);
        cart.setPrice(pd);
        jedis.set(userId.toString(), JSONObject.toJSONString(cart));
        modelMap.addAttribute("cart", cart);
        return "gouwuche";
    }
}
