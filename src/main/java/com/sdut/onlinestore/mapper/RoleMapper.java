package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.Menu;
import com.sdut.onlinestore.pojo.Role;
import com.sdut.onlinestore.pojo.RoleExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {
    @Insert("insert role values(null,#{role.rname},#{role.is_deleted})")
    Integer insertRole(@Param("role") Role role);

    @Insert("insert role_menu values(null,#{role.rid},#{menu.id})")
    Integer insertRoleMenu(@Param("role") Role role , @Param("menu")Menu menu);

    @Update("update role set is_deleted = 1 where rid=#{role.rid}")
    Integer delelteRole(@Param("role") Role role);

    @Delete("delete from role_menu where rid=#{role.rid}")
    Integer deleteRoleMenuByRole(@Param("role") Role role);



    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}