package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.Item;
import com.sdut.onlinestore.pojo.Order;
import com.sdut.onlinestore.pojo.OrderExample;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(String oid);


    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    @Select("select * from `order` where uid = #{uid}")
    List<Order> selectByUid(String uid);

//    @Update("update `order` set state = 3 where oid=#{oid}")
//    int updateByOidToPay(String oid);

    @Select("select  *  from `order` where oid =#{oid}  ")
    Order selectByOid(String oid);

    @Select("select count(uid)  from `order` where uid =#{uid}")
    long selectCountByUid(String uid);

    @Insert("insert into `order`( uid,oid,ordertime,total,state) values " +
            "(#{order.user.uid},#{order.oid},#{order.ordertime},#{order.total},#{order.state})")
    int insertInit(@Param("order") Order order);

//    @Update("update `order` set state = 2 where oid=#{oid}")
//    int updateByOidToConfirm(String oid);

    @Update("update `order` set state = #{state} where oid=#{oid} ")
    int updateStateByOid(String oid, Integer state);
}