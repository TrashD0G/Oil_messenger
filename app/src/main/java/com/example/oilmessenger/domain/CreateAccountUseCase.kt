package com.example.oilmessenger.domain

import com.example.oilmessenger.data.CreateAccountFirebaseImp

class CreateAccountUseCase(private val createAccountFirebase: CreateAccountFirebaseImp) {

    suspend fun createUser(email: String, password: String, firstName: String, lastName: String): String {
        return createAccountFirebase.createUser(email, password, firstName, lastName)
    }

}