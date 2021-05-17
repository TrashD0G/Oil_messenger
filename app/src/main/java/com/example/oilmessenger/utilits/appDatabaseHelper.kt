package com.example.oilmessenger.utilits

import com.example.oilmessenger.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID:String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var USER: User
lateinit var CURRENT_USER: FirebaseUser

const val TYPE_TEXT = "text"


const val NODE_USERS = "users"
const val CHILD_PHOTO_URL = "photoUrl"
const val FOLDER_PROFILE_IMAGE ="profile_image"
const val CHILD_STATE = "state"
const val CHILD_PUBLIC_MESSAGES = "public messages"
const val NODE_MESSAGES = "messages"
const val CHILD_ID = "id"
const val CHILD_TEXT = "text"
const val CHILD_TYPE = "type"
const val CHILD_FROM = "from"
const val CHILD_TIMESTAMP = "timeStamp"