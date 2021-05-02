package com.springcloud.demo.stock.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author liangpr
 * @date 2021/02/27
 */
@RestController()
@RequestMapping("stock/")
@RefreshScope
public class StockCommonController {

    @Value("${config.message}")
    private String configMessage;
    
    @Autowired
    private RedissonClient redissonClient;
    
    @GetMapping("intercept/{count}")
    /*@HystrixCommand(fallbackMethod = "interceptFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40")
    })*/
    public String intercept(@PathVariable("count") Integer count){
        String lockKey = "stock-intercept";
        RLock lock = redissonClient.getLock(lockKey);
        try{
            if(count < 0){
                throw new RuntimeException("count 不能为负数");
            }
            if(!lock.tryLock()){
                return "stock2 try lock fail";
            }
            /*if(RandomUtils.nextInt(3) == 2){
                throw new UnsupportedOperationException();
            }*/
            return "success-stock2, config-message["+ configMessage +"]";
        } finally{
            if(lock.isHeldByCurrentThread()){
                lock.forceUnlock();
            }
        }
    }
}
