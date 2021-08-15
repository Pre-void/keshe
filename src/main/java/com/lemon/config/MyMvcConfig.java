package com.lemon.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author fanjiawen
 * @date 2019/6/20 17:10
 */

//@EnableWebMvc
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("/user/login").setViewName("index");
        registry.addViewController("/user/register").setViewName("register");
        registry.addViewController("/user/forget").setViewName("forget");
        registry.addViewController("/user/home").setViewName("home");
        registry.addViewController("/user/yanzhengma").setViewName("yanzhengma");
        registry.addViewController("/user/index").setViewName("index");
        registry.addViewController("").setViewName("index");
//        registry.addViewController("/user/cart").setViewName("cart");
        registry.addViewController("/user/place").setViewName("place");
        registry.addViewController("/user/detail").setViewName("detail");
        registry.addViewController("/user/info").setViewName("userInfo");
        registry.addViewController("/user/order").setViewName("userOrder");
        registry.addViewController("/user/site").setViewName("userSite");
        registry.addViewController("/user/admin").setViewName("adminIndex");
        registry.addViewController("/user/goodsList").setViewName("goods");
        registry.addViewController("/user/adminList").setViewName("adminList");
        registry.addViewController("/user/memberList").setViewName("memberList");
        registry.addViewController("/user/orderList").setViewName("orderList");
        registry.addViewController("/user/goodsEdit").setViewName("goodsEdit");
        registry.addViewController("/user/orderEdit").setViewName("orderEdit");
        registry.addViewController("/user/memberEdit").setViewName("memberEdit");
        registry.addViewController("/user/goodsAdd").setViewName("goodsAdd");
        registry.addViewController("/user/orderAdd").setViewName("orderAdd");
        registry.addViewController("/user/memberAdd").setViewName("memberAdd");
        registry.addViewController("/user/adminEdit").setViewName("adminEdit");
        registry.addViewController("/user/adminAdd").setViewName("adminAdd");
        registry.addViewController("/user/list").setViewName("list");
        registry.addViewController("/user/success").setViewName("success");
    }
}


//注册拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //super.addInterceptors(registry);
//                //静态资源；  *.css , *.js
//                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/","/user/**");
//            }
//        };
//        return adapter;
//
//    }

