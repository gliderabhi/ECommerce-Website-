package com.example.user.repository

import com.example.user.model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface LoginRepository : JpaRepository<Users, Long> {
    fun findByUsername(username: String): Users?

    fun findByEmail(email: String): Users?
}