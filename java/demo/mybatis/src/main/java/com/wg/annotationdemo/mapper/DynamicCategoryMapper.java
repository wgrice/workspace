package com.wg.annotationdemo.mapper;

import com.wg.annotationdemo.pojo.*;
import com.wg.annotationdemo.provider.*;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DynamicCategoryMapper {

    @InsertProvider(type = CategoryDynamicSqlProvider.class, method = "add")
    void add(Category Category);

    @DeleteProvider(type = CategoryDynamicSqlProvider.class, method = "delete")
    void delete(long id);

    @UpdateProvider(type = CategoryDynamicSqlProvider.class, method = "update")
    void update(Category Category);

    @SelectProvider(type = CategoryDynamicSqlProvider.class, method = "get")
    Category get(long id);

    @SelectProvider(type = CategoryDynamicSqlProvider.class, method = "list")
    List<Category> list();
}
