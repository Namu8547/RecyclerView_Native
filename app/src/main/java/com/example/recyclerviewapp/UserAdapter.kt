package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewapp.model.UserModel

class UserAdapter(val userList : List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.AdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return AdapterViewHolder(view)
    }

     override fun onBindViewHolder(holder: UserAdapter.AdapterViewHolder, position: Int) {

        val item = userList[position]
        holder.userName.text = item.name
        holder.userEmail.text = item.email
         Glide.with(holder.itemView.context)
             .load(item.avatar)
             .placeholder(R.drawable.ic_launcher_foreground)
             .error(R.drawable.ic_launcher_background)
             .into(holder.userAvatar)
    }

    override fun getItemCount(): Int {
        return userList.size

    }
    class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val userName = view.findViewById<TextView>(R.id.name_text)
        val userEmail = view.findViewById<TextView>(R.id.mail_text)
        val userAvatar = view.findViewById<ImageView>(R.id.user_avatar)

    }

}