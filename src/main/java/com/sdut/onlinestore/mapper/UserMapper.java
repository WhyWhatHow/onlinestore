package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.pojo.User;
import com.sdut.onlinestore.pojo.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("select rid from user where uid = #{uid}")
    Integer selectByUserToRid(User user);

    @Update("update user set rid=null where rid=#{role.rid}")
    Integer deleteUserRole(@Param("role") Role role);

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String uid);

    @Select("select * from user where username =#{username}")
    User selectByUsername(String username);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}