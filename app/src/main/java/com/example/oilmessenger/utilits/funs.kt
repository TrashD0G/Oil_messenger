package com.example.oilmessenger.utilits

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.example.oilmessenger.R
import com.squareup.picasso.Picasso

fun showToast(message: String){
    Toast.makeText(MESSENGER_ACTIVITY, message, Toast.LENGTH_LONG).show()
}

fun ImageView.downloadAndSetImage(url: String){
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.user256)
        .into(this)
}


fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
