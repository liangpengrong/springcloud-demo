package com.springcloud.demo.gateway.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author Liang Pengrong
 * @date 2021/03/21
 */
@Component
@Order(765)
public class GatewayFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(StringUtils.isEmpty(token) || !"123456".equals(token)){
            System.out.println("========token fail");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.NO_CONTENT);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }
}
