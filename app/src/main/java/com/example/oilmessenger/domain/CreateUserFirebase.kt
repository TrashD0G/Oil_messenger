package com.example.oilmessenger.domain

interface CreateUserFirebase {
    suspend fun createUser(email: String, password: String, firstName: String, lastName: String): String
}