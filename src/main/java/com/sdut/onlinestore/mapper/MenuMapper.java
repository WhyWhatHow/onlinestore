package com.sdut.onlinestore.mapper;


import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.MenuExample;

import java.util.List;

import com.sdut.onlinestore.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenuMapper {

    @Select("select * from menu ,role_menu rm  where rid = #{rid} and rm.mid = menu.id")
    List<Menu> selectByRole(Role role);

    int countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}