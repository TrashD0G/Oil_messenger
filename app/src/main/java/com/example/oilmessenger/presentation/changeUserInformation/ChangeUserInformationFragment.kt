package com.example.oilmessenger.presentation.changeUserInformation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oilmessenger.databinding.FragmentChangeUserInformationBinding
import com.example.oilmessenger.domain.User
import com.example.oilmessenger.presentation.messengerActivity.MainActivity
import com.example.oilmessenger.presentation.messengerActivity.TAG
import com.example.oilmessenger.presentation.messengerActivity.user
import com.example.oilmessenger.utilits.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class ChangeUserInformationFragment : Fragment() {

    private var _binding: FragmentChangeUserInformationBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding
        _binding = FragmentChangeUserInformationBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.buttonUserChangePhoto.setOnClickListener { changePhotoUser() }

        //Firebase
        val ref = FirebaseDatabase.getInstance().getReference(NODE_USERS).child(CURRENT_UID)
        val UserInfListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {

            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)

                binding.textViewFirstNameUserChange.text = user?.getFirstName()
                binding.textViewLastNameUserChange.text = user?.getLastName()

                if (user?.photoUrl!!.isNotEmpty()){
                   binding.imageViewUserChange.downloadAndSetImage(user.photoUrl)
                }

            }
        }

        ref.addListenerForSingleValueEvent(UserInfListener)

        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(CURRENT_UID)
            path.putFile(uri).addOnCompleteListener { task1 ->
                if (task1.isSuccessful){
                    path.downloadUrl.addOnCompleteListener{ task2 ->
                        if (task2.isSuccessful){
                            val photoUrl = task2.result.toString()
                            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_PHOTO_URL)
                                .setValue(photoUrl)
                                .addOnCompleteListener{
                                    if (it.isSuccessful){
                                        Log.i(TAG,"Фото присвоено в user")
                                        binding.imageViewUserChange.downloadAndSetImage(photoUrl)
                                        MESSENGER_ACTIVITY.updateUserPhoto(photoUrl)
                                        showToast("Фото изменено!")
                                        user.photoUrl = photoUrl

                                    }
                                }

                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun changePhotoUser(){
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600,600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(activity as MainActivity,this)
    }


}

