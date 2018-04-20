package com.wg.demo;

import com.wg.demo.pojo.Category;
import com.wg.demo.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class TestMybatisTransaction {

    public static void main(String[] args) throws IOException, SQLException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        Category c= new Category();
        c.setName("12423534");
        session.insert("addCategory", c);
        c.setName("1242353464746874846737667657456786876895798579780694578467467");
        session.insert("addCategory", c);

        session.commit();
        session.close();

    }
}
