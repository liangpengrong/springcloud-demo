package com.springcloud.demo.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.demo.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);
    
    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}