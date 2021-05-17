package com.example.oilmessenger.di

import com.example.oilmessenger.presentation.createAccount.CreateAccountFragment
import com.example.oilmessenger.presentation.enterAccount.EnterAccountFragment
import dagger.Component


@Component(modules = [CreateAccountProvides::class, EnterAccountProvides::class])
interface AppComponent {

    fun injectCreateAccountFragment(createAccountFragment: CreateAccountFragment)
    fun injectEnterAccountFragment(enterAccountFragment: EnterAccountFragment)
}