package com.wg.annotationdemo;

import com.wg.annotationdemo.mapper.OrderMapper;
import com.wg.annotationdemo.mapper.ProductMapper;
import com.wg.annotationdemo.pojo.Category;
import com.wg.annotationdemo.pojo.Order;
import com.wg.annotationdemo.pojo.OrderItem;
import com.wg.annotationdemo.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestAnnotationManyToMany {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config-annotation.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();

        listOrder(session);

        session.commit();
        session.close();
    }

    private static void listOrder(SqlSession session) {
        OrderMapper mapper =session.getMapper(OrderMapper.class);
        List<Order> os = mapper.listOrder();
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois= o.getOrderItems();
            if(null!=ois){
                for (OrderItem oi : ois) {
                    System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
                }
            }

        }
    }
}
