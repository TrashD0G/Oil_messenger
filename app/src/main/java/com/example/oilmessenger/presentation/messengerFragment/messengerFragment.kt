package com.example.oilmessenger.presentation.messengerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oilmessenger.databinding.FragmentMessengerBinding
import com.example.oilmessenger.domain.CommonMessenger
import com.example.oilmessenger.presentation.messengerActivity.MessengerAdapter
import com.example.oilmessenger.presentation.messengerActivity.user
import com.example.oilmessenger.utilits.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class messengerFragment : Fragment() {

    private var _binding: FragmentMessengerBinding? = null
    private val binding get() = _binding!!

    //Firebase
    private lateinit var messengerList:  ArrayList<CommonMessenger>
    private lateinit var mRecyclerView: RecyclerView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Binding
        _binding = FragmentMessengerBinding.inflate(inflater, container, false)
        val view = binding.root

        mRecyclerView = binding.chatRecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        messengerList = ArrayList()



        binding.chatBtnSendMessage.setOnClickListener {

            val message = binding.editTextChatInput.text.toString()
            if (message.isEmpty()){
                showToast("Введите сообщение")
            } else {

                sendMessage(user.userFirstName, user.userLastName, user.photoUrl,user.id,message,
                    "txt")
                binding.editTextChatInput.setText("")
                view.hideKeyboard()
            }
        }




        return view
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            getUserData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserData() {
        var dbref = REF_DATABASE_ROOT.child(CHILD_PUBLIC_MESSAGES)
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    messengerList.clear()
                    for (dataSnapshot: DataSnapshot in snapshot.children){
                        val userInf = dataSnapshot.getValue(CommonMessenger::class.java)



                        if (CURRENT_UID == userInf?.id){


                            if (userInf.photoUrl != user.photoUrl){


                                REF_DATABASE_ROOT.child(CHILD_PUBLIC_MESSAGES).child(dataSnapshot.key!!).child("photoUrl").setValue(user.photoUrl)



                            }
                        }


                        messengerList.add(userInf!!)
                    }

                    mRecyclerView.adapter = MessengerAdapter(messengerList)
                    var adapter = mRecyclerView.adapter
                    if (adapter != null) {
                        mRecyclerView.scrollToPosition(adapter.itemCount - 1)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }

}