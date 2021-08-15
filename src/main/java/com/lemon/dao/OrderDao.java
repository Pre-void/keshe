package com.lemon.dao;

import com.lemon.entity.OrderEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作订单表
 */
@Mapper
public interface OrderDao {

    String TABLE_NAME = "goods_order";

    String INSERT_FIELDs = "orderId,goodsId,orderDate,orderAddress,goodsCount";

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDs, "), values(#{orderID},#{goodsID},#{orderDate},#{orderAddress},#{goodsCount})"})
    void insertOrder(OrderEntity orderEntity);

    @Select({"select * from ", TABLE_NAME, "where orderID=#{orderID}"})
    OrderEntity selectOrder(String orderId);

    @Select({"select * from", TABLE_NAME})
    List<OrderEntity> findAll();
}
