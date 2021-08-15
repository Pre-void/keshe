package com.lemon.controller;

import com.lemon.dao.GoodsDao;
import com.lemon.dao.OrderDao;
import com.lemon.dao.UserDao;
import com.lemon.entity.GoodsEntity;
import com.lemon.entity.OrderEntity;
import com.lemon.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user/*")
public class AdminController {

    @Autowired(required = false)
    GoodsDao goodsDao;

    @Autowired(required = false)
    UserDao userDao;

    @Autowired(required = false)
    OrderDao orderDao;

    @RequestMapping("memberList")
    public String findUser(Model model) {
        List<UserEntity> userEntities = userDao.findAll();

        model.addAttribute("members", userEntities);

        return "memberList";
    }

    @RequestMapping("goodsList")
    public String findGoods(Model model) {
        List<GoodsEntity> goodsEntities = goodsDao.findAll();

        model.addAttribute("goods", goodsEntities);
        return "goods";
    }

    @RequestMapping("orderList")
    public String findOrders(Model model) {
        List<OrderEntity> orderEntities = orderDao.findAll();
        model.addAttribute("orders", orderEntities);
        return "orderList";
    }

    @ResponseBody
    @RequestMapping("/user/editGoods")
    public String editGoods(){

        System.err.println("运行到这里了");
        return "修改成功";
    }

    @ResponseBody
    @RequestMapping("/user/addGoods")
    public String addGoods(HttpServletRequest request){
        String id = request.getParameter("goodsId");
        String name = request.getParameter("goodsName");
        Double price = Double.parseDouble(request.getParameter("goodsPrice"));
        String detail = request.getParameter("goodsDetail");
        System.err.println(id+name+price+detail);
        goodsDao.UpdateGoods(id,name,price,detail);
        return "修改成功";
    }


}
