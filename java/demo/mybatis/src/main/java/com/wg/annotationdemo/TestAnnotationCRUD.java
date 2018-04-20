package com.wg.annotationdemo;

import com.wg.annotationdemo.mapper.CategoryMapper;
import com.wg.annotationdemo.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestAnnotationCRUD {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config-annotation.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();

        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
        //add(mapper);
        //add(mapper);
        //update(mapper, 12);
        //delete(mapper, 13);
        get(mapper, 12);

        session.commit();
        session.close();
    }

    private static void update(CategoryMapper mapper, long id) {
        Category c= mapper.getCategory(id);
        c.setName("修改了的Category名稱");
        mapper.updateCategory(c);
        listAll(mapper);
    }

    private static void get(CategoryMapper mapper, long id) {
        Category c= mapper.getCategory(id);
        System.out.println(c.getName());
    }

    private static void delete(CategoryMapper mapper, long id) {
        mapper.deleteCategory(id);
        listAll(mapper);
    }

    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.addCategory(c);
        listAll(mapper);
    }

    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.getAllCategory();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

}
