package com.springcloud.demo.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.springcloud.demo.order.dao.OrderMapper;
import com.springcloud.demo.order.service.feign.StockUrlTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公共的订单控制层,列出来关于订单的全部接口
 * @author liangpr
 * @date 2021/02/27
 */
@RestController
@RequestMapping("order/")
/*@DefaultProperties(defaultFallback = "defaultFallback")*/
public class OrderCommonController {
    
    @Resource
    private StockUrlTemplate stockUrlTemplate;
    @Autowired
    private OrderMapper orderMapper;
    
    @GetMapping("create/{id}")
    /*@HystrixCommand(fallbackMethod = "createFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @SentinelResource(value = "order-create", 
            blockHandlerClass = CustomerBlockedHandler.class, blockHandler = "createBlockedHandler",
            fallback = "createFallback", exceptionsToIgnore = RuntimeException.class)
    public String create(@PathVariable("id") String id){
        System.out.println("======"+Thread.currentThread().getName());
        /*try {
            Thread.sleep(RandomUtils.nextInt(4) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 调用减库存
        if(Integer.parseInt(id) < 0){
            throw new RuntimeException("ID 不能为负数");
        }
        String s;
        System.out.println(s = stockUrlTemplate.interceptStock(1));
        return "hello====="+s;
    }

    @GetMapping("create2/{id}")
    @SentinelResource(value = "order-create2", fallback = "createFallback")
    public String create2(@PathVariable("id") String id){
        return "hello=====create2";
    }

    public String createFallback(String id){
        return "createFallback";
    }

    public String defaultFallback(){
        return "默认兜底";
    }
}
