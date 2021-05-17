package com.example.oilmessenger.presentation.createAccount.viewModelCreateAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oilmessenger.domain.CreateAccountUseCase
import java.lang.IllegalArgumentException

class ViewModelCreateAccountFactory (private val createAccount: CreateAccountUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelCreateAccount::class.java)){
            return ViewModelCreateAccount(createAccount) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}