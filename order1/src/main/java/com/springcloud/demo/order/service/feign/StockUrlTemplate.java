package com.springcloud.demo.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author liangpr
 * @date 2021/02/27
 */
@FeignClient(name = "STOCK-SERVICE", fallbackFactory = StockFallbackFactory.class)
@Component
public interface StockUrlTemplate {
    
    @GetMapping("/stock/intercept/{count}")
    String interceptStock(@PathVariable("count") Integer count);
}
