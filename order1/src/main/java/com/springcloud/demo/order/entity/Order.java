package com.springcloud.demo.order.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order implements Serializable {
    
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    private String orderName;

    private Boolean state;

    private static final long serialVersionUID = 1L;
}