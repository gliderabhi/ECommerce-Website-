package com.example.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class UserController(
    private val restTemplate: RestTemplate
) {

    @GetMapping("/user/")
    fun getUser(): String {
        val msg = restTemplate.getForObject("lb://products/products/1", String::class.java)
        println(msg)
        return msg + "Lets get something"

    }
}