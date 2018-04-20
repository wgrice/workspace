package com.wg.annotationdemo;
import com.wg.annotationdemo.mapper.DynamicCategoryMapper;
import com.wg.annotationdemo.pojo.Category;
import com.wg.annotationdemo.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDynamicSqlProvider {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config-annotation.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();

        DynamicCategoryMapper mapper=session.getMapper(DynamicCategoryMapper.class);
        listAll(mapper);
        Category c=new Category();
//        c.setName("unknow category");
//        mapper.add(c);
//        c.setId(12);
//        c.setName("category 3");
//        mapper.update(c);
        Category cc= mapper.get(15);
        System.out.println(cc);
//        mapper.delete(15);
//        listAll(mapper);

        session.commit();
        session.close();
    }

    private static void listAll(DynamicCategoryMapper mapper)
    {
        List<Category> cs =mapper.list();
        for(Category c:cs)
            System.out.println(c);
        System.out.println();
    }
}
