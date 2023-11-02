package com.example.product.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsController {

    @GetMapping("/products/{id}")
    fun getProduct(): String {
        return "Hello from products page"
    }
}