package com.easyToBuy.APIGateWay.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import  static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return route("Product-Service")
                .route(RequestPredicates.path("/api/product"), http("http://localhost:8086"))
                .build();
       }

       @Bean
        public RouterFunction<ServerResponse> orderServiceRoute(){
        return route("Order-Service")
                .route(RequestPredicates.path("/api/order"), http("http://localhost:8084"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return route("Inventory-Service")
                .route(RequestPredicates.path("/api/inventory"), http("http://localhost:8085"))
                .build();
    }
}
