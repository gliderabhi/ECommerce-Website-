package com.example.user.controller

import com.example.user.model.LoginCredentials
import com.example.user.model.LoginResponse
import com.example.user.model.Users
import com.example.user.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
//@RequestMapping("/user")
class UserController(
    private val restTemplate: RestTemplate,
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@RequestBody user: Users): LoginResponse {
        return authenticationService.registerUser(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody credentials: LoginCredentials): LoginResponse? {
        return authenticationService.loginUser(credentials)
    }

    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") token: String): Boolean {
        return authenticationService.logoutUser(token)
    }

    @PostMapping("/password/reset")
    fun resetPassword(@RequestBody email: String): LoginResponse? {
        return authenticationService.resetPassword(email)
    }
}
