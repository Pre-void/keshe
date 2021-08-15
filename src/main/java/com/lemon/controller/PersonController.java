package com.lemon.controller;

import com.lemon.dao.CartDao;
import com.lemon.dao.GoodsDao;
import com.lemon.dao.UserDao;
import com.lemon.entity.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/*")
public class PersonController {

    @Autowired(required = false)
    UserDao userDao;

    @Autowired(required = false)
    GoodsDao goodsDao;
    @Autowired(required = false)
    CartDao cartDao;

    @RequestMapping("/info")
    public String UserInfo(HttpServletRequest request, Map<String, String> map, Model model) {

        List<GoodsEntity> goods = goodsDao.findAll();
        int i = 0;
        for (GoodsEntity goodsEntity : goods) {
            model.addAttribute("goods" + i, goodsEntity);
            i++;
        }
        String user = request.getParameter("user");
        String address = userDao.selectAddress(user);
        String name = userDao.selectName(user);
        String tele = userDao.selectTele(user);
        map.put("address", address);
        map.put("tele", tele);
        map.put("name", name);

        return "userInfo";
    }

    @RequestMapping("/order")
    public String UserOrder(Model model) {
        List<GoodsEntity> goods = goodsDao.findAll();
        int i = 0;
        for (GoodsEntity goodsEntity : goods) {
            model.addAttribute("goods" + i, goodsEntity);
            i++;
        }
        return "userOrder";
    }

    @RequestMapping("/site")
    public String UserSite(HttpServletRequest request, Map<String, String> map, HttpSession session) {
        String user = request.getParameter("user");
        System.err.println(user);
        String address = userDao.selectAddress(user);
        String name = userDao.selectName(user);
        String tele = userDao.selectTele(user);
        map.put("address", address);
        map.put("tele", tele);
        map.put("name", name);
        return "userSite";
    }

    @RequestMapping("/update")
    public String UpdateAddress(HttpServletRequest request, HttpSession session) {
        String user = (String) session.getAttribute("loginUser");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String tele = request.getParameter("tele");

        userDao.updateAddress(address, name, tele, user);
        return "redirect:/user/site?user=" + user;
    }

    @RequestMapping("/info_add_cart")
    public String info_add_cart(HttpServletRequest request) {
        String user = request.getParameter("user");
        String id = request.getParameter("id");
        System.err.println(user);
        System.err.println(id);
        cartDao.insertToCart(user, id);
        return "redirect:/user/info?user=" + user;
    }

}
