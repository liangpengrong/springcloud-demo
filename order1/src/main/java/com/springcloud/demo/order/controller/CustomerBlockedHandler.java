package com.springcloud.demo.order.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 *
 * @author Liang Pengrong
 * @date 2021/03/26
 */
public class CustomerBlockedHandler {

    public static String createBlockedHandler(String id, BlockException exception){
        return "createBlockedHandler===全局兜底";
    }
}
