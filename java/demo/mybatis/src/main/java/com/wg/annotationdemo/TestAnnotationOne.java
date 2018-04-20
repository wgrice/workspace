package com.wg.annotationdemo;

import com.wg.annotationdemo.mapper.ProductMapper;
import com.wg.annotationdemo.pojo.Category;
import com.wg.annotationdemo.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestAnnotationOne {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config-annotation.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();

        ProductMapper mapper=session.getMapper(ProductMapper.class);
        listAll(mapper);

        session.commit();
        session.close();
    }

    private static void listAll(ProductMapper mapper) {
        List<Product> ps = mapper.listProduct();
        for (Product p : ps) {
            System.out.println(p);
            Category c = p.getCategory();
            System.out.println("\t in \t"+c);
        }
    }
}
