package com.example.oilmessenger.domain

interface EnterUserFirebase {
    suspend fun signInUser(email: String, password: String): String
}