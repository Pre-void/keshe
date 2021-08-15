package com.lemon.dao;

import com.lemon.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 操作用户表
 */
@Mapper
public interface UserDao {

    String TABLE_NAME = "user";
    String INSERT_FIELDS = "user, pwd, email";

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{user},#{pwd},#{email})"})
    void insertUser(UserEntity userEntity);

    @Select({"select pwd from", TABLE_NAME, "where user=#{user}"})
    String selectPwd(String user);

    @Update({"update", TABLE_NAME, "set pwd=#{pwd} where user=#{user}"})
    void updatePwd(String user, String pwd);

    @Select({"select address from", TABLE_NAME, "where user=#{user}"})
    String selectAddress(String user);

    @Select({"select name from", TABLE_NAME, "where user=#{user}"})
    String selectName(String user);

    @Select({"select tele from", TABLE_NAME, "where user=#{user}"})
    String selectTele(String user);

    @Update({"update", TABLE_NAME, "set address=#{0},name=#{1},tele=#{2} where user=#{3}"})
    void updateAddress(String address, String name, String tele, String user);

    @Select({"select * from", TABLE_NAME})
    List<UserEntity> findAll();

}
