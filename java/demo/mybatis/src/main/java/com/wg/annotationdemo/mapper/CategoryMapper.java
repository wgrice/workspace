package com.wg.annotationdemo.mapper;

import com.wg.annotationdemo.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {

    @Insert("insert into category_ ( name ) values (#{name})")
    void addCategory(Category Category);

    @Delete("delete from category_ where id=#{id}")
    void deleteCategory(long id);

    @Update("update category_ set name=#{name} where id=#{id}")
    void updateCategory(Category Category);

    @Select("select * from category_ where id=#{id}")
    Category getCategory(long id);

    @Select("select * from category_")
    List<Category> getAllCategory();

    @Select("select * from category_")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "products", javaType = List.class, column = "id", many=@Many(select = "com.wg.annotationdemo.mapper.ProductMapper.listProductByCategory"))
    })
    List<Category> listCategory();
}
