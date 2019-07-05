package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.Item;
import com.sdut.onlinestore.pojo.Order;
import com.sdut.onlinestore.pojo.OrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from order where uid = #{uid}")
    List<Order> selectByUid(String uid);

    @Update("udpate order set state = 3 where oid = #{oid}")
    int updateByOidToPay(String oid);

    Order selectByOid(String oid);
    @Select("select count(uid)  from order where uid =#{uid}")
    long selectCountByUid(String uid);
}