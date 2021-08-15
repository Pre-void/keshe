package com.lemon.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartDao {

    String TABLE_NAME = "shop_cart";
    String INSERT_FIELDS = "user,goods_id";

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{0},#{1})"})
    void insertToCart(String user, String goodsId);

    @Select({"select goods_id from", TABLE_NAME, "where user=#{user}"})
    List<String> findAll(String user);

    @Delete({"delete from", TABLE_NAME, "where goods_id=#{id}"})
    void DelGoods(String id);

    @Select({"select count(*) from", TABLE_NAME})
    Integer SelectCount();
}
