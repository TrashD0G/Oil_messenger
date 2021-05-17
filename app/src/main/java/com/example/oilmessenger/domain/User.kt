package com.example.oilmessenger.domain

  class User(

      val userEmail: String = "",
      val userFirstName: String = "",
      val userLastName: String = "",
      var photoUrl: String = "",
      var state: String = "",
      var id: String = "",
  )

  {

      fun getEmail(): String {
          return userEmail
      }

      fun getFirstName(): String {
          return userFirstName
      }

      fun getLastName(): String {
          return userLastName
      }


 }