package com.example.oilmessenger.utilits

import com.example.oilmessenger.domain.CommonMessenger
import com.example.oilmessenger.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    USER = User()
    CURRENT_USER = AUTH.currentUser!!
    CURRENT_UID = AUTH.currentUser?.uid.toString()

}

fun sendMessage(firstName:String, lastName:String,photoUrl:String, receivingUserID: String, message: String, typeText: String,) {

    val  commonUser = CommonMessenger(firstName,lastName,photoUrl,receivingUserID,message)

    REF_DATABASE_ROOT.child(CHILD_PUBLIC_MESSAGES).push().setValue(commonUser)


}



