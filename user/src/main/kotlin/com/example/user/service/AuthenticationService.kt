package com.example.user.service

import com.example.user.model.LoginCredentials
import com.example.user.model.LoginResponse
import com.example.user.model.Users
import com.example.user.repository.LoginRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.Objects.hash

@Service
class AuthenticationService(
    private val loginRepository: LoginRepository
) {

    fun registerUser(user: Users): LoginResponse {
        // Check if username or email already exists
        val existingUserUsername = loginRepository.findByUsername(user.username)
        if (existingUserUsername != null) {
            // Username already exists
            return LoginResponse(false, "User already exists")
        }

        val existingUserEmail = loginRepository.findByEmail(user.email)
        if (existingUserEmail != null) {
            // Email already exists
            return LoginResponse(false, "User already exists")
        }

        // Persist user information to the data source
        val insertedUser = loginRepository.save(user)

        return LoginResponse(true, insertedUser)
    }

    fun loginUser(credentials: LoginCredentials): LoginResponse? {
        // Retrieve user data from the data source
        val retrievedUser = loginRepository.findByUsername(credentials.username) ?: // Username not found
        return LoginResponse(false, "Invalid Credentials")

        // Check if password matches the retrieved user's password
        if (credentials.password == retrievedUser.password) {
            // Generate authentication token
            val token = generateToken(retrievedUser)
            return LoginResponse(
                true,
                Users(token = token, username = retrievedUser.username, email = retrievedUser.email)
            )
        }

        // Invalid credentials
        return LoginResponse(false, "Invalid Credentials")
    }

    fun logoutUser(token: String): Boolean {
        // Validate authentication token
        // Invalidate token in the data source
        return true // Indicates successful logout
    }

    fun resetPassword(email: String): LoginResponse? {
        // Validate email address
        val existingUser = loginRepository.findByEmail(email)
            ?: // Email not found
            return LoginResponse(false, "Email not found")

        // Generate password reset token
        val resetToken = generateToken(existingUser)

        // Send password reset instructions to the email address
//        sendPasswordResetEmail(existingUser, resetToken)

        // Persist reset token in the data source
        existingUser.token = resetToken
        val resetUser = loginRepository.save(existingUser)

        return LoginResponse(true, resetUser) // Indicates successful password reset initiation
    }

    fun generateToken(user: Users): String {
        val uuid = UUID.randomUUID().toString()
        val timestamp = System.currentTimeMillis()
        val hashedToken = hash(uuid + timestamp)
        return hashedToken.toString()
    }
}