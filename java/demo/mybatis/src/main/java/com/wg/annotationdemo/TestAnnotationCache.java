package com.wg.annotationdemo;

import com.wg.annotationdemo.mapper.CategoryMapper;
import com.wg.annotationdemo.mapper.CategoryPageMapper;
import com.wg.annotationdemo.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestAnnotationCache {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config-annotation.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();

        CategoryMapper mapper1=session.getMapper(CategoryMapper.class);
        get(mapper1,22);
        get(mapper1,22);

        session.commit();
        session.close();

        SqlSession session2 = sf.openSession();
        CategoryMapper mapper2=session2.getMapper(CategoryMapper.class);
        get(mapper2,22);
        get(mapper2,22);

        session2.commit();
        session2.close();
    }

    private static void get(CategoryMapper mapper, long id) {
        Category c= mapper.getCategory(id);
        System.out.println(c.getName());
    }
}
