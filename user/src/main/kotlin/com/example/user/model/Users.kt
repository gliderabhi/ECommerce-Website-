package com.example.user.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    var token: String = ""
)
