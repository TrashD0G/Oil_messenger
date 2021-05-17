package com.example.oilmessenger.presentation.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oilmessenger.R
import com.example.oilmessenger.domain.User
import com.example.oilmessenger.utilits.downloadAndSetImage

class UsersAdapter(private var userList: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.users_item,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        if (currentItem.photoUrl.isNotEmpty()){
            holder.imageViewUser.downloadAndSetImage(currentItem.photoUrl)
        }

        if (currentItem.state == "в сети"){
            holder.imageStateUser?.visibility = View.VISIBLE
            holder.textViewUsersLastOnline?.text  = "в сети"
        }
        else {
            holder.imageStateUser?.visibility = View.INVISIBLE
            holder.textViewUsersLastOnline?.text  = "не в сети"
        }


        holder.textViewUsersFirstLastName.text = currentItem.userFirstName +" "+ currentItem.userLastName

    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imageViewUser = itemView.findViewById<ImageView>(R.id.imageViewUser)
        val imageStateUser = itemView.findViewById<ImageView>(R.id.userState)
        val textViewUsersFirstLastName = itemView.findViewById<TextView>(R.id.textViewUsersFirstLastName)
        val textViewUsersLastOnline = itemView.findViewById<TextView>(R.id.textViewUsersLastOnline)

    }

}