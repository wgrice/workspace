package com.wg.annotationdemo.mapper;

import com.wg.annotationdemo.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryPageMapper {
    @Select(" select * from category_ limit #{start},#{count}")
    List<Category> listByPage(@Param("start") long start, @Param("count") long count);
}
