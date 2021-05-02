package com.springcloud.demo.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author liangpr
 * @date 2021/03/21
 */
@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder){
        RouteLocatorBuilder.Builder routes = locatorBuilder.routes();
        routes.route("baidu-route", r->r
                .path("/guonei")
                .uri("https://news.baidu.com/guonei")
        ).build();
        return routes.build();
    }
}
