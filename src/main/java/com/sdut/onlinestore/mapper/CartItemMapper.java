package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.CartItem;
import com.sdut.onlinestore.pojo.CartResult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartItemMapper {

    @Select("select ci.id,ci.uid,ci.pid,ci.quantity,ci.fee,product.price  ,pname , image_url `imageUrl` from cart_item ci, product where uid = #{uid} and ci.pid = product.pid and ci.is_finished = false and ci.is_deleted = false ")
//    @Results({ @Result(column="id", property="id", jdbcType= JdbcType.INTEGER ),
//            @Result(column="ci.uid", property="cartItem.uid", jdbcType=JdbcType.VARCHAR),
//            @Result(column="ci.pid", property="cartItem.pid", jdbcType=JdbcType.VARCHAR),
//            @Result(column = "ci.quantity" ,property="product.quantity",jdbcType = JdbcType.INTEGER),
//            @Result(column = "ci.price", property = "product.price", jdbcType = JdbcType.DOUBLE),
//            @Result(column = "pname",property =  "product.pname", jdbcType = JdbcType.VARCHAR),
//            @Result(column = "image_url",property =  "product.imageUrl", jdbcType = JdbcType.VARCHAR)
//    })
    List<CartResult> selectByUid(String uid);

    @Update("update cart_item  set is_deleted  = true where pid =#{pid}")
    int deletByPid(String pid);

//    @Select("select * from cart_item where pid = #{pid}")
//    CartItem selectByPid(String s, String pid);

    @Update("update cart_item set quantity = #{quantity}, fee= #{fee} where id =#{id}")
    int updateByPrimaryKeyToNum(CartItem cartItem);

    @Select("select * from cart_item where pid =#{pid} and uid = #{uid} and is_finished =false and is_deleted =false ")
    CartItem selectByPidAndUid(String pid, String uid);

    @Insert("INSERT INTO cart_item (uid,pid,quantity,created,fee) values(#{uid},#{pid},#{quantity},#{created},#{fee})")
    int insertByVo(CartItem cartItem);

    @Update("update cart_item set is_finished  =true where uid =#{uid} and id =#{id}")
    int updateToFinished(Long id, String uid);
    @Update("update cart_item  set is_deleted  = true where id =#{id}")
    int deletById(Integer id);
//    @Update("update cart_item set is_finished = true where id = and uid =#{user.uid}")
//    int updateToFinishedInList(@Par);
}