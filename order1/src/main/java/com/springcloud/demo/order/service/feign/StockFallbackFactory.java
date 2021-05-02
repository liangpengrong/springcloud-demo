package com.springcloud.demo.order.service.feign;

import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author liangpr
 * @date 2021/02/27
 */
@Component
public class StockFallbackFactory implements FallbackFactory<StockUrlTemplate> {

    @Override
    public StockUrlTemplate create(Throwable throwable) {
        String s = "downgrade processing, error message \n "+ ExceptionUtils.getFullStackTrace(throwable);
        return count->s;
    }
}
