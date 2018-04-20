package com.wg.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.wg.demo.pojo.Category;
import com.wg.demo.pojo.Product;

public class TestMybatisOne {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
        }

        session.commit();
        session.close();

    }
}