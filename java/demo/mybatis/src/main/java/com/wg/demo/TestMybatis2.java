package com.wg.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.wg.demo.pojo.Category;

public class TestMybatis2 {
    public static final int ID = 5;

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        String q = "new ";
        List<Category> cs = session.selectList("listCategoryByName", q);
        for(Category c: cs)
            System.out.println(c.getName());

        session.commit();
        session.close();
    }
}
