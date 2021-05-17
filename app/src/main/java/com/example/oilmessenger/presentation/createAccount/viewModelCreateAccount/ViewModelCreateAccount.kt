package com.example.oilmessenger.presentation.createAccount.viewModelCreateAccount

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oilmessenger.domain.CreateAccountUseCase
import com.example.oilmessenger.presentation.messengerActivity.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import kotlin.coroutines.CoroutineContext

class ViewModelCreateAccount(private val createAccount: CreateAccountUseCase) : ViewModel(),
    CoroutineScope {

    private val _emailValidate = MutableLiveData<Boolean>()
    val emailValidate = _emailValidate
    private val _passwordValidate = MutableLiveData<Boolean>()
    val passwordValidate = _passwordValidate
    private val _firstNameValidate = MutableLiveData<Boolean>()
    val firstNameValidate = _firstNameValidate
    private val _lastNameValidate = MutableLiveData<Boolean>()
    val lastNameValidate = _lastNameValidate


    private val _createAccountResult = MutableLiveData<String>()
    val createAccountResult = _createAccountResult


    lateinit var email: String
    lateinit var password: String
    lateinit var firstName: String
    lateinit var lastName: String


    fun checkInput(
        emailValue: String,
        passwordValue: String,
        firstNameValue: String,
        lastNameValue: String,
    ) {

        validateEmail(emailValue)
        validatePassword(passwordValue)
        validateFirstName(firstNameValue)
        validateLastName(lastNameValue)

        if (_emailValidate.value == true && _passwordValidate.value == true && _firstNameValidate.value == true && _lastNameValidate.value == true) {

            Log.i(TAG, "Create account: All Data is correct!")

            launch(Dispatchers.IO) {
                createAccount()
            }

        }
    }

    private suspend fun createAccount() {
        when (createAccount.createUser(email, password, firstName, lastName)) {
            "Аккаунт создан!" -> _createAccountResult.postValue("Аккаунт создан!")
            "ERROR_EMAIL_ALREADY_IN_USE" -> _createAccountResult.postValue("Email занят!")
            "Ошибка!" -> _createAccountResult.postValue("Ошибка!")
        }
    }

    private fun validateEmail(emailValue: String) {
        if (emailValue.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            Log.i(TAG, "Create account: Valid Email!")
            email = emailValue
            _emailValidate.value = true
        } else {
            Log.i(TAG, "Create account: Invalid Email!")
            _emailValidate.value = false
        }
    }

    private fun validatePassword(passwordValue: String) {
        val PASSWORD_PATTERN: Pattern = Pattern.compile(
            "^" +
                    "(?=.*[a-zA-Z])" +
                    "(?=\\S+$)" +
                    ".{8,16}" +
                    "$"
        )

        if (passwordValue.isEmpty()) {
            Log.i(TAG, "Create account: Password is empty!$passwordValue")
            _passwordValidate.value = false

        } else if (!PASSWORD_PATTERN.matcher(passwordValue).matches()) {
            Log.i(TAG, "Create account: Password too weak!")
            _passwordValidate.value = false
        } else {
            Log.i(TAG, "Create account: Valid password!")
            password = passwordValue
            _passwordValidate.value = true
        }

    }

    private fun validateFirstName(firstNameValue: String) {

        val FIRST_NAME_PATTERN: Pattern = Pattern.compile(
            "^" +
                    "(?=.*[а-яА-Я])" +
                    "(?=\\S+$)" +
                    ".{2,20}" +
                    "$"
        )

        if (firstNameValue.isEmpty()) {
            Log.i(TAG, "Create account: First name is empty!$firstNameValue")
            _firstNameValidate.value = false

        } else if (!FIRST_NAME_PATTERN.matcher(firstNameValue).matches()) {
            Log.i(TAG, "Create account: first name not Pattern!")
            _firstNameValidate.value = false
        } else {
            Log.i(TAG, "Create account: first name is correct!")
            firstName = firstNameValue
            _firstNameValidate.value = true
        }
    }


    private fun validateLastName(lastNameValue: String) {

        val LAST_NAME_PATTERN: Pattern = Pattern.compile(
            "^" +
                    "(?=.*[а-яА-Я])" +
                    "(?=\\S+$)" +
                    ".{2,20}" +
                    "$"
        )

        if (lastNameValue.isEmpty()) {
            Log.i(TAG, "Create account: last name is empty!$lastNameValue")
            _lastNameValidate.value = false

        } else if (!LAST_NAME_PATTERN.matcher(lastNameValue).matches()) {
            Log.i(TAG, "Create account: last name not Pattern!")
            _lastNameValidate.value = false
        } else {
            Log.i(TAG, "Create account: last name is correct!")
            lastName = lastNameValue
            _lastNameValidate.value = true
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


}