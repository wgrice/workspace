package com.wg.annotationdemo.mapper;

import com.wg.annotationdemo.pojo.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {

    @Select("select * from product_ where cid = #{cid}")
    List<Product> listProductByCategory(long cid);

    @Select(" select * from product_ ")
    @Results({
            @Result(property="category", column="cid", one=@One(select="com.wg.annotationdemo.mapper.CategoryMapper.getCategory"))
    })
    List<Product> listProduct();

    @Select("select * from product_ where id=#{pid}")
    Product getProduct(long pid);
}
