package com.example.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class UserApplication

fun main(args: Array<String>) {
	runApplication<UserApplication>(*args)

}
