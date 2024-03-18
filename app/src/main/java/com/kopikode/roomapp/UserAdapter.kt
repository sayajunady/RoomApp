package com.kopikode.roomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kopikode.roomapp.entity.User

class UserAdapter (var list: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var fullName: TextView
        var email: TextView
        var phone: TextView

        init {
            fullName = view.findViewById(R.id.full_name)
            email = view.findViewById(R.id.email)
            phone = view.findViewById(R.id.phone)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fullName.text = list[position].fullName
        holder.email.text = list[position].email
        holder.phone.text = list[position].phone
    }
}