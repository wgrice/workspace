package com.wg.demo;

import com.wg.demo.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatisDynamicSQL {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
/*
        //test where
        System.out.println("查询所有");
        List<Product> ps = session.selectList("getProductAutoWhere");
        for (Product p : ps) {
            System.out.println(p);
        }

        System.out.println("模糊查询1");
        Map<String,Object> params1 = new HashMap<String,Object>();
        params1.put("name","a");
        ps = session.selectList("getProductAutoWhere", params1);
        for (Product p : ps) {
            System.out.println(p);
        }

        System.out.println("模糊查询2");
        Map<String,Object> params2 = new HashMap<String,Object>();
        params2.put("price", 20);
        ps = session.selectList("getProductAutoWhere", params2);
        for (Product p : ps) {
            System.out.println(p);
        }

        System.out.println("模糊查询3");
        Map<String,Object> params3 = new HashMap<String,Object>();
        params3.put("name","new");
        params3.put("price", 20);
        ps = session.selectList("getProductAutoWhere", params3);
        for (Product p : ps) {
            System.out.println(p);
        }
*/
/*
        //test set
        listAllProduct(session);
        updateProduct1(session);
        listAllProduct(session);
        updateProduct2(session);
        listAllProduct(session);
        updateProduct3(session);
        listAllProduct(session);
*/

/*      //test choose
        Map<String, Object> map = new HashMap<String, Object>();
        chooseProduct(session, map);
        //map.put("name", "product");
        //chooseProduct(session, map);
        map.put("price", 30);
        chooseProduct(session, map);
*/
        //chooseProductBind(session);

        List<Integer> ids = new ArrayList();
        ids.add(1);
        ids.add(3);
        ids.add(5);

        List<Product> ps = session.selectList("listProductForeach",ids);
        for (Product p : ps) {
            System.out.println(p);
        }


        session.commit();
        session.close();

    }

    private static void chooseProductBind(SqlSession session) {
        System.out.println("bind select");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "king");
        List<Product> ps = session.selectList("listProduct", map);
        for(Product p:ps)
            System.out.println(p);
    }

    private static void chooseProduct(SqlSession session, Map map) {
        System.out.println("Choose select");
        List<Product> ps = session.selectList("listChooseProduct", map);
        for(Product p:ps)
            System.out.println(p);
    }

    private static void updateProduct1(SqlSession session) {
        System.out.println("选择性更新");
        Product p = new Product();
        p.setName("product j");
        p.setId(7);
        session.update("updateProductAutoSet", p);
    }

    private static void updateProduct2(SqlSession session) {
        System.out.println("选择性更新");
        Product p = new Product();
        p.setPrice(77.77);
        p.setId(7);
        session.update("updateProductAutoSet", p);
    }

    private static void updateProduct3(SqlSession session) {
        System.out.println("选择性更新");
        Product p = new Product();
        p.setPrice(88.88);
        p.setName("product king");
        p.setId(7);
        session.update("updateProductAutoSet", p);
    }


    private static void listAllProduct(SqlSession session) {
        List<Product> ps = session.selectList("listAllProductOnly");
        for (Product p : ps)
            System.out.println(p);
    }
}