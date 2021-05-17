package com.example.oilmessenger.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.oilmessenger.R
import com.example.oilmessenger.databinding.ActivityAccountBinding
import com.example.oilmessenger.presentation.messengerActivity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AccountActivity : AppCompatActivity() {
     val TAG = "MyTag"

    private lateinit var binding: ActivityAccountBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountBinding.inflate(layoutInflater)
        val view = binding.root


        setContentView(view)
    }

    override fun onStart() {
        super.onStart()

        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null){

            Log.i(TAG, "MainScreenAcntivity: ACCOUNT ACTIVITY Текущий ользователь: " + currentUser.email)


            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

        } else{
            Log.i(TAG, "MainScreenAcntivity: Нет пользователя: $currentUser")
            Navigation.findNavController(this,R.id.account_fragment).navigate(R.id.action_loadScreenFragment_to_enterAccountFragment)
        }

    }
}