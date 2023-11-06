package com.example.apigateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder

@Configuration
class GatewayRoutesConfiguration {

    @Bean
    fun myRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("CART") { r ->
                r.path("/cart/**")
                    .uri("lb://CART")
            }
            .route("JEWELLERY") { r ->
                r.path("/product/jewellery/**")
                    .uri("lb://JEWELLERY")
            }
            .route("products") { r ->
                r.path("/products/**")
                    .uri("lb://products")
            }
            .route("WATCHES") { r ->
                r.path("/products/watches/**")
                    .uri("lb://WATCHES")
            }
            .route("USER") { r ->
                r.path("/user/**")
                    .uri("lb://user")
            }
            .route("WOOLEN") { r ->
                r.path("/products/woolen/**")
                    .uri("lb://WOOLEN")
            }
            // Add more routes if needed
            .build()
    }
}