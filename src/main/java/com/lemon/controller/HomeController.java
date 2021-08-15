package com.lemon.controller;

import com.lemon.dao.CartDao;
import com.lemon.dao.GoodsDao;
import com.lemon.dao.UserDao;
import com.lemon.entity.GoodsEntity;
import com.lemon.entity.MygoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/*")
public class HomeController {

    @Autowired(required = false)
    GoodsDao goodsDao;

    @Autowired(required = false)
    CartDao cartDao;

    @Autowired(required = false)
    UserDao userDao;

    @RequestMapping("/place")
    public String place(Model model, Map<String, Double> map, Map<String, String> map1, HttpSession session) {
        String user = (String) session.getAttribute("loginUser");
        System.err.println(user);
        List<String> strings = cartDao.findAll(user);
        ArrayList<GoodsEntity> goodsEntities = new ArrayList<>();
        for (String string : strings) {
            goodsEntities.add(goodsDao.selectGoodsById(string));

        }
        //System.err.println(goodsEntities);
        double sum = 0;
        double i = 0;
        for (GoodsEntity goodsEntity : goodsEntities) {
            sum = sum + goodsEntity.getGoodsPrice();
            i++;
        }
        //System.out.println(sum);
        map.put("sum", sum);
        map.put("count", i);
        model.addAttribute("cart", goodsEntities);
        String address = userDao.selectAddress(user);
        String name = userDao.selectName(user);
        String tele = userDao.selectTele(user);
        map1.put("address", address);
        map1.put("tele", tele);
        map1.put("name", name);
        return "place";
    }

    @RequestMapping("/xiangqing")
    public String showGoods(Model model, HttpServletRequest request, Map<String, Integer> map) {

        String id = request.getParameter("id");
        System.out.println(id);
        GoodsEntity goodsEntity = goodsDao.selectGoodsById(id);
        model.addAttribute("detail", goodsEntity);
        int count = cartDao.SelectCount();
        map.put("cart_count", count);
        return "detail";
    }
    @RequestMapping("/xiangqing1")
    public String show_Goods(Model model, HttpServletRequest request, Map<String, Integer> map) {

        String id = request.getParameter("id");
        System.out.println(id);
        MygoodsEntity mygoodsEntity = goodsDao.selectGoodsById1(id);
        model.addAttribute("detail1", mygoodsEntity);
        int count = cartDao.SelectCount();
        map.put("cart_count", count);
        return "detail1";
    }
    @RequestMapping("/add_cart")
    public String add_cart(HttpServletRequest request) {
        String user = request.getParameter("user");
        String id = request.getParameter("id");

        System.err.println(user);
        System.err.println(id);

        cartDao.insertToCart(user, id);
        return "redirect:/user/xiangqing?id=" + id;
    }


    @RequestMapping("/cart")
    public String cart(HttpServletRequest request, Model model, Map<String, Double> map) {
        String user = request.getParameter("user");
        List<String> strings = cartDao.findAll(user);
        ArrayList<GoodsEntity> goodsEntities = new ArrayList<>();
        for (String string : strings) {
            goodsEntities.add(goodsDao.selectGoodsById(string));

        }
        System.err.println(goodsEntities);
        double sum = 0;
        double i = 0;
        for (GoodsEntity goodsEntity : goodsEntities) {
            sum = sum + goodsEntity.getGoodsPrice();
            i++;
        }
        //System.out.println(sum);
        map.put("sum", sum);
        map.put("count", i);
        model.addAttribute("cart", goodsEntities);

        return "cart";

    }

    @RequestMapping("/del_cart")
    public String del_cart(HttpServletRequest request) {
        String goodsId = request.getParameter("id");
        String user = request.getParameter("user");

        cartDao.DelGoods(goodsId);

        return "redirect:/user/cart?user=" + user;

    }

    @RequestMapping("/buy")
    public String buy(HttpServletRequest request, Model model, Map<String, Double> map, HttpSession session, Map<String, String> map1) {
        String user = (String) session.getAttribute("loginUser");
        String id = request.getParameter("id");
        ArrayList<GoodsEntity> goodsEntities = new ArrayList<>();
        GoodsEntity goodsEntity = goodsDao.selectGoodsById(id);
        goodsEntities.add(goodsEntity);
        model.addAttribute("good", goodsEntities);
        map.put("price", goodsEntity.getGoodsPrice());
        String address = userDao.selectAddress(user);
        String name = userDao.selectName(user);
        String tele = userDao.selectTele(user);
        map1.put("address", address);
        map1.put("tele", tele);
        map1.put("name", name);
        return "place";
    }

    @RequestMapping("/find")
    public String find(HttpServletRequest request,Model model,Map<String, Integer> map1){
        String name=request.getParameter("name");

        List<MygoodsEntity> mygoodsEntities = goodsDao.selectGoods(name);
        //model.addAttribute("find_goods",mygoodsEntities);
        int i=1;
        for (MygoodsEntity mygoodsEntity : mygoodsEntities) {
            model.addAttribute("find_goods" + i, mygoodsEntity);
            //httpSession.setAttribute("detail"+i,goodsEntity);
            i++;
        }
        int count = cartDao.SelectCount();
        map1.put("cart_count", count);
        return "list";
    }

}
