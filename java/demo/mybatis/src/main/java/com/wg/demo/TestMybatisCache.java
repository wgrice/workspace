package com.wg.demo;

import com.wg.demo.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatisCache {
    public static final int ID = 5;

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        get(session);
        get(session);

        session.commit();
        session.close();

        SqlSession session2 = sqlSessionFactory.openSession();

        get(session2);
        get(session2);

        session2.commit();
        session2.close();

    }

    private static void get(SqlSession session) {
        System.out.println();
        Category c = session.selectOne("getCategory", 22);
        System.out.println(c.getName());

    }
}
