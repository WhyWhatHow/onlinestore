package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.CartItem;
import com.sdut.onlinestore.vo.CartItemVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartItemMapper {

    @Select("select * from cart_item where uid = #{uid} and is_finished= false")
    List<CartItem> selectByUid(String uid);

    @Update("update cart_item  set is_deleted  = true where pid =#{pid}")
    int deletByPid(String pid);

//    @Select("select * from cart_item where pid = #{pid}")
//    CartItem selectByPid(String s, String pid);

    @Update("update cart_item set num = #{num} where id =#{id}")
    int updateByPrimaryKeyToNum(CartItem cartItem);

    @Select("select * from cart_item where pid =#{pid} and uid =#{uid}")
    CartItem selectByPidAndUid(String pid, String uid);

    @Insert("INSERT INTO cart_item (uid,pid, pname,plocation,num,created) values(#{uid},#{pid},#{pname},#{plocation},#{num},#{created})")
    int insert(CartItem cartItem);
}