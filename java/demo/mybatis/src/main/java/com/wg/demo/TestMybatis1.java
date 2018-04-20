package com.wg.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.wg.demo.pojo.Category;

public class TestMybatis1 {
    public static final int ID=5;

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();

        listAll(session);

        Category c = new Category();
        c.setName("new Category");
        session.insert("addCategory", c);
        listAll(session);

        c = session.selectOne("getCategory", ID);
        c.setName("changed Category");
        session.update("updateCategory", c);
        listAll(session);

        c.setId(ID);
        session.delete("deleteCategory", c);
        listAll(session);

    }

    private static void listAll(SqlSession session)
    {
        System.out.println();
        List<Category> cs=session.selectList("listCategory");
        for(Category c: cs){
            System.out.println(c.getName());
        }
    }
}
