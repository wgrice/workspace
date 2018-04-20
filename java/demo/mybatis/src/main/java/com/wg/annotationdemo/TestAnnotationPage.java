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

public class TestAnnotationPage {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config-annotation.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();

        //分页 0-4 5
        System.out.println("page:0-4 count:5");
        page(session, 0,5);
        System.out.println("page:77-81 count:5");
        page(session, 77,5);
        System.out.println("page:50-66 count:17");
        page(session, 50,17);

        session.commit();
        session.close();
    }

    private static void page(SqlSession session, long start, long count) {
        CategoryPageMapper mapper = session.getMapper(CategoryPageMapper.class);

        List<Category>  cs =mapper.listByPage(start, count);
        for (Category c : cs) {
            System.out.println(c);
        }
    }
}
