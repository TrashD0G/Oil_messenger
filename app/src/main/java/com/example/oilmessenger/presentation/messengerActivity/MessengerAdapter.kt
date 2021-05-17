package com.example.oilmessenger.presentation.messengerActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.oilmessenger.R
import com.example.oilmessenger.domain.CommonMessenger
import com.example.oilmessenger.utilits.CURRENT_UID
import com.example.oilmessenger.utilits.downloadAndSetImage

class MessengerAdapter(private var messangerList : ArrayList<CommonMessenger>) : RecyclerView.Adapter<MessengerAdapter.MessageViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false)

        return MessageViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentItem = messangerList[position]

        if (currentItem.id == CURRENT_UID){
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = messangerList[position].message
        } else{
            holder.blockUserMessage.visibility = View.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedUserName.text = messangerList[position].userFirstName + " " + messangerList[position].userLastName
            holder.chatReceivedMessage.text = messangerList[position].message

            if (currentItem.photoUrl.isNotEmpty()){
                holder.receivedImageUser.downloadAndSetImage(currentItem.photoUrl)
            }
        }
    }

    override fun getItemCount(): Int {
        return messangerList.size
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val blockUserMessage =  itemView.findViewById<ConstraintLayout>(R.id.block_user_message)
        val blockReceivedMessage = itemView.findViewById<LinearLayoutCompat>(R.id.block_received_message)
        val chatUserMessage = itemView.findViewById<TextView>(R.id.chat_user_message)

        val chatReceivedMessage = itemView.findViewById<TextView>(R.id.chat_received_message)
        val chatReceivedUserName = itemView.findViewById<TextView>(R.id.chat_received_user_name)
        val receivedImageUser = itemView.findViewById<ImageView>(R.id.imageView_received_user)
    }

}