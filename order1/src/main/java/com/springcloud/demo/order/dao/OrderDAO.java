package com.springcloud.demo.order.dao;

import com.springcloud.demo.order.entity.Order;

import java.util.List;

/**
 *
 * @author Liang Pengrong
 * @date 2021/03/28
 */

public interface OrderDAO {
    
    List<Order> listOrders(Order where);
    
    int updateOrder(Integer orderId);
}
