package com.example.oilmessenger.domain

import com.example.oilmessenger.data.EnterAccountFirebaseImp

class EnterAccountUseCase(private val enterAccountFirebase: EnterAccountFirebaseImp) {

    suspend fun enterUser(email: String, password: String): String {
        return enterAccountFirebase.signInUser(email, password)
    }
}