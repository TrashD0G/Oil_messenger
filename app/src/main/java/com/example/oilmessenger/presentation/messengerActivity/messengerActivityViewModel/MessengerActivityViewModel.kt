package com.example.oilmessenger.presentation.messengerActivity.messengerActivityViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessengerActivityViewModel: ViewModel() {
    private val _firstName = MutableLiveData<String>()
    val firstNameValidate = _firstName
    private val _lastName = MutableLiveData<String>()
    val lastNameValidate = _lastName


}