package com.example.oilmessenger.presentation.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oilmessenger.R
import com.example.oilmessenger.databinding.FragmentFriendsBinding
import com.example.oilmessenger.domain.User
import com.example.oilmessenger.utilits.CURRENT_UID
import com.example.oilmessenger.utilits.NODE_USERS
import com.example.oilmessenger.utilits.REF_DATABASE_ROOT
import com.google.firebase.database.*


class FriendsFragment : Fragment(R.layout.fragment_friends) {

    //Binding
    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    //Firebase
    var dbref = REF_DATABASE_ROOT.child(NODE_USERS)
    private lateinit var usersList:  ArrayList<User>
    private lateinit var mRecyclerView: RecyclerView




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        val view = binding.root

        mRecyclerView = binding.usersList
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        usersList = ArrayList()

        return view
    }



    override fun onResume() {
        super.onResume()
        getUserData()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getUserData() {

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                usersList.clear()
                if (snapshot.exists()){

                    for (dataSnapshot: DataSnapshot in snapshot.children){
                        val userInf = dataSnapshot.getValue(User::class.java)
                        if ( dataSnapshot.key != CURRENT_UID){

                            usersList.add(userInf!!)
                        }

                    }

                    mRecyclerView.adapter = UsersAdapter(usersList)

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }



}