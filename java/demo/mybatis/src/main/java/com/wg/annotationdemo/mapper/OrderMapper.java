package com.wg.annotationdemo.mapper;

import com.wg.annotationdemo.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {

    @Select("select * from order_")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderItems", javaType = List.class, column = "id",
                    many = @Many(select = "com.wg.annotationdemo.mapper.OrderItemMapper.listItemByOrder"))
    })
    List<Order> listOrder();
}
