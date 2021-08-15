package com.lemon.controller;


import com.lemon.VerificationCode.SmsApiHttpSendTest;
import com.lemon.dao.CartDao;
import com.lemon.dao.GoodsDao;
import com.lemon.dao.UserDao;
import com.lemon.entity.GoodsEntity;
import com.lemon.entity.MygoodsEntity;
import com.lemon.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired(required = false)
    UserDao userDao;
    @Autowired(required = false)
    GoodsDao goodsDao;
    @Autowired(required = false)
    CartDao cartDao;

    private String yzm;
    private String refresh = null;

    @RequestMapping("/home")
    public String home(Model model, Map<String, Integer> map) {
        List<GoodsEntity> goods = goodsDao.findAll();
        int i = 0;
        for (GoodsEntity goodsEntity : goods) {
            model.addAttribute("goods" + i, goodsEntity);
            i++;
        }
        int count = cartDao.SelectCount();
        map.put("cart_count", count);
        return "home";
    }

    @RequestMapping("/yzm")
    public String yanzheng(HttpServletRequest request, Map<String, String> map) throws Exception {

        String tele = request.getParameter("tele");
        System.out.println(tele);
        SmsApiHttpSendTest test = new SmsApiHttpSendTest();
        yzm = test.getRandNum();
        System.out.println(yzm);
        test.execute(tele, yzm);
        map.put("shouji", tele);

        if (tele != null) {
            refresh = "test";
            map.put("test", refresh);
        }
        return "yanzhengma";
    }

    @RequestMapping("/next")
    public String next(HttpServletRequest request, Map<String, String> map) {
        String tele = request.getParameter("tele");
        String yzm = request.getParameter("yzm");

        if (yzm.equals(this.yzm)) {
            map.put("tele", tele);
            return "register";
        } else {
            map.put("y_error", "验证码错误");
            map.put("y_tele", tele);
            return "yanzhengma";
        }
    }

    @RequestMapping("/ulogin")
    public String login(HttpServletRequest request, HttpSession httpSession, Map<String, String> map, UserEntity userEntity, Model model, Map<String, Integer> map1) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

//        System.err.println(user+"0");
//        System.err.println(userDao.selectPwd(user)+"1");
//        System.err.println(pwd+"2");

        if (user == "" || pwd == "") {
            // System.out.println("step1");
            map.put("no", "请输入用户名或密码");
            return "index";
        }
        if (userDao.selectPwd(user) == null) {
            map.put("no_exist", "用户名不存在");
            return "index";
        }
        if (user.equals("admin") && pwd.equals("admin"))
            return "adminIndex";
        if (userDao.selectPwd(user).equals(pwd)) {
            // System.out.println("lalala");
            map.put("user", user);
            httpSession.setAttribute("loginUser", userEntity.getUser());

            List<GoodsEntity> goods = goodsDao.findAll();
            int i = 0;
            for (GoodsEntity goodsEntity : goods) {
                model.addAttribute("goods" + i, goodsEntity);
                //httpSession.setAttribute("detail"+i,goodsEntity);
                i++;
            }
            int count = cartDao.SelectCount();
            map1.put("cart_count", count);
            return "home";
        } else {
            map.put("error", "用户名或密码错误");
            return "index";
        }

    }

    @RequestMapping("/uregister")
    public String register(HttpServletRequest request, Map<String, String> map) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String pwd1 = request.getParameter("pwd1");
        String email = request.getParameter("email");

        System.out.println(pwd.equals(pwd1));
        if (pwd.equals(pwd1)) {
            String flag = userDao.selectPwd(user);

            if (flag == null) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUser(user);
                userEntity.setPwd(pwd);
                userEntity.setEmail(email);
                userDao.insertUser(userEntity);
                return "index";
            } else {
                map.put("exist", "用户名已存在");
                return "register";
            }
        } else {
            map.put("diff", "两次输入的密码不一致");
            return "register";
        }

    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().invalidate();

        return "index";
    }
}
