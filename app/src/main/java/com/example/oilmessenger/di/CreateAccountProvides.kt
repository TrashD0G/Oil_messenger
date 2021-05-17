package com.example.oilmessenger.di

import com.example.oilmessenger.data.CreateAccountFirebaseImp
import com.example.oilmessenger.domain.CreateAccountUseCase
import com.example.oilmessenger.presentation.createAccount.viewModelCreateAccount.ViewModelCreateAccountFactory
import dagger.Module
import dagger.Provides

@Module
class CreateAccountProvides {

    @Provides
    fun provideCreateAccountUseCase(createAccountFirebase: CreateAccountFirebaseImp): CreateAccountUseCase {
        return CreateAccountUseCase(createAccountFirebase)
    }

    @Provides
    fun provideCreateAccountFirebaseImp(): CreateAccountFirebaseImp {
        return CreateAccountFirebaseImp()
    }

    @Provides
    fun provideViewModelAccountFactory(createAccountUseCase: CreateAccountUseCase): ViewModelCreateAccountFactory {
        return ViewModelCreateAccountFactory(createAccountUseCase)
    }

}