package com.example.sampleproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.R
import com.example.sampleproject.network.model.User
import kotlinx.android.synthetic.main.view_user_layout.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder> {

    var userList: ArrayList<User> = ArrayList()
    private var context: Context
    private var inflater: LayoutInflater

    private var onInteractListener: OnInteractListener? = null


    constructor(context: Context) : super() {
        this.context = context
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = inflater.inflate(R.layout.view_user_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        /*Sample UI only.*/
        holder.tvName.text = "Name: " + user.name
        holder.tvEmail.text = "Email: " + user.email
        holder.tvWebsite.text = "Website:" + user.website

        holder.llRootView.setOnClickListener{
            onInteractListener?.onUserClick(user)
        }
    }

    fun populateUserList(userList: ArrayList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    fun insertUser(user : User){
        this.userList.add(user)
        notifyDataSetChanged()
    }

    fun setOnInteractListener(onInteractListener: OnInteractListener) {
        this.onInteractListener = onInteractListener
    }

    interface OnInteractListener {
        fun onUserClick(user: User)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tv_name
        val tvEmail = itemView.tv_email
        val tvWebsite = itemView.tv_website
        val llRootView = itemView.ll_rootview
    }
}
