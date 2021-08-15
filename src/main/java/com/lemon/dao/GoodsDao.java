package com.lemon.dao;


import com.lemon.entity.GoodsEntity;
import com.lemon.entity.MygoodsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 操作商品表
 */
@Mapper
public interface GoodsDao {

    String TABLE_NAME = "goods";
    String TABLE_NAME1="my_goods";
    String INSERT_FIELDS = "goodsId,goodsName,goodsPrice,goodsDate";
    String str = "电脑";
    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{goodsId},#{goodsName},#{goodsPrice},#{goodsDate})"})
    void inserGoods(GoodsEntity goodsEntity);

    @Select({"select * from", TABLE_NAME, "where goods_id=#{goodsID}"})
    GoodsEntity selectGoodsById(String goodsId);

    @Select({"select * from", TABLE_NAME1, "where id=#{id}"})
    MygoodsEntity selectGoodsById1(String id);

    @Select({"select * from", TABLE_NAME})
    List<GoodsEntity> findAll();

    @Select( {"select * from",TABLE_NAME1,"where kind=#{name} order by count desc limit 15"})
    List<MygoodsEntity> selectGoods(String name);

    @Update({"update", TABLE_NAME, "set goods_name=#{1},goods_price=#{2},goods_detail=#{3} where goods_id=#{0}"})
    void UpdateGoods(String GoodsId,String GoodsName,Double GoodsPrice,String GoodsDetail);
}
