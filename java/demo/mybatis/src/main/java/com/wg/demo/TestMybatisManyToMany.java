package com.wg.demo;

import com.wg.demo.pojo.Order;
import com.wg.demo.pojo.OrderItem;
import com.wg.demo.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatisManyToMany {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

//        listOrderDetail(session);
//        getOrderDetail(session);

/*        listOrderDetail(session);
        addOrderItem(session);
        listOrderDetail(session);
        deleteOrderItem(session);
        listOrderDetail(session);*/

        listOrderDetail(session);
        addOrderItemByID(session);
        listOrderDetail(session);

        session.commit();
        session.close();

    }

    private static void listOrderDetail(SqlSession session) {
        List<Order> os = session.selectList("listOrderDetail");
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois = o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(), oi.getNumber());
            }
        }
        System.out.println();
    }

    public static void getOrderDetail(SqlSession session) {
        Order o = session.selectOne("getOrderDetail", 1);
        List<OrderItem> ois = o.getOrderItems();
        for (OrderItem oi : ois) {
            System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(), oi.getNumber());
        }
    }

    private static void deleteOrderItem(SqlSession session) {
        Order o1 = session.selectOne("getOrderDetail", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        session.delete("deleteOrderItem", oi);
    }

    private static void addOrderItem(SqlSession session) {
        Order o1 = session.selectOne("getOrderDetail", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        oi.setNumber(200);

        session.insert("addOrderItem", oi);
    }

    private static void addOrderItemByID(SqlSession session) {
        Order o = new Order();
        Product p = new Product();
        OrderItem oi = new OrderItem();
        o.setId(1);
        p.setId(5);

        oi.setProduct(p);
        oi.setOrder(o);
        oi.setNumber(300);

        session.insert("addOrderItem", oi);
    }
}
