package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.Item;
import com.sdut.onlinestore.pojo.ItemExample;

import java.util.List;

import com.sdut.onlinestore.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ItemMapper {
    int countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(String itemid);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(String itemid);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    @Select("select * from item  i,product p where  p.pid = o.pid and o.oid = ?")
    List<Item> selectByOid(String oid);

    @Select("<script> " +
            "select id, resource_name as resourceName, resource_type as resourceType from t_resource_dic where dic_type = #{ditType} and resource_type " +
            "in <foreach item='item' index='index' collection='resources' open='(' separator=',' close=')'> #{item} </foreach> " +
            "</script>")
//    List<ResourceDicVo> getResourceDics(@Param("resources")List<Integer> resources, @Param("ditType")Integer ditType);

    @Insert("<script>"+"insert into item values <foreach item='item' index ='index' collections='list'  open='(' separator=',' close=')'> #{item.itemid},#{item.quantity},#{item.total},#{item.product.pid},#{item.order.oid} " )
    int insertByList(@Param("list") List<Item> list);

//    List<Item> selectItemByOidList(@Param("order") List<Order> list);
}