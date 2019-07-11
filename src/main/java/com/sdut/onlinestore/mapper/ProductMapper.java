package com.sdut.onlinestore.mapper;

import com.sdut.onlinestore.pojo.Category;
import com.sdut.onlinestore.pojo.Product;
import com.sdut.onlinestore.pojo.ProductExample;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductMapper {



    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(String pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);


    @Select("select * from product")
    List<Product> selectAll();

    @Select("select count(pid) from product")
    long selectCount();

    @Select("select count(pid) from product where cid =#{cid}")
    long selectCountByCategory(Integer cid);

    @Select("select * from product where cid = #{cid}")
    List<Product> selectByCategory(Integer cid);

    @Delete("update   product  set is_deleted = true  where pid =#{pid}")
    int updateToDeleted(String pid);

    @Select("select * from product where name like concat('%',#{panme},'%')")
    List<Product> selectByLike(String pname);

    @Update("update  product set is_selling = #{state} where pid = #{pid}")
    int updateByPidToChangeState(String pid, Boolean state);

    //    @Select("select * from product where pname like concat('%',#{pname},'%')  ")
    List<Product> selectToList(Product product);

    //    @Select("select * from product ")
    long selectToListCount(Product product);

//    @Select(" ")
    List<Product> selectToSideShow();


//    int updateByPidToChangeState(Product product);
}
