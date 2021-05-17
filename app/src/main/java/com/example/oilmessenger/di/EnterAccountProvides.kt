package com.example.oilmessenger.di

import com.example.oilmessenger.data.EnterAccountFirebaseImp
import com.example.oilmessenger.domain.EnterAccountUseCase
import com.example.oilmessenger.presentation.enterAccount.viewModelEnterAccount.ViewModelEnterAccountFactory
import dagger.Module
import dagger.Provides

@Module
class EnterAccountProvides {

    @Provides
    fun provideEnterAccountUseCase(enterAccountFirebase: EnterAccountFirebaseImp): EnterAccountUseCase {
        return EnterAccountUseCase(enterAccountFirebase)
    }

    @Provides
    fun provideEnterAccountFirebaseImp(): EnterAccountFirebaseImp {
        return EnterAccountFirebaseImp()
    }

    @Provides
    fun provideViewModelEnterFactory(enterAccountUseCase: EnterAccountUseCase): ViewModelEnterAccountFactory {
        return ViewModelEnterAccountFactory(enterAccountUseCase)
    }
}