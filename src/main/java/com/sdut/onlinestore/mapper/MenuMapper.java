package com.sdut.onlinestore.mapper;


import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.MenuExample;

import java.util.List;

import com.sdut.onlinestore.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenuMapper {

    @Select("select * from menu ,role_menu rm  where rid = #{rid} and rm.mid = menu.id")
    List<Menu> selectByRole(Role role);

    @Select("select * from menu where is_deleted=false")
    List<Menu> selectAllMenu();

    @Insert("insert menu values(null,#{menu.name},#{menu.uri},#{menu.parentid},#{menu.type},{menu.is_deleted})")
    Integer insertMenu(@Param("menu") Menu menu);

    @Update("update menu set is_deleted = 1 where id=#{menu.id}")
    Integer deleteMenu(@Param("menu") Menu menu);

    @Delete("delete from role_menu where mid=#{menu.id}")
    Integer deleteRoleMenuByMenu(@Param("menu") Menu menu);

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