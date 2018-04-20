package com.wg.annotationdemo;

import com.wg.annotationdemo.mapper.CategoryMapper;
import com.wg.annotationdemo.pojo.Category;
import com.wg.annotationdemo.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestAnnotationMany {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config-annotation.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();

        CategoryMapper mapper=session.getMapper(CategoryMapper.class);
        listAll(mapper);

        session.commit();
        session.close();
    }

    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.listCategory();
        for (Category c : cs) {
            System.out.println(c);
            List<Product> ps = c.getProducts();
            for (Product p : ps) {
                System.out.println("\t"+p);
            }
        }
    }
}
