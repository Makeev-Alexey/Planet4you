package com.example.myapplication.firstFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.Data.API.UserInfo
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemBinding

class UserlistAdapter(): RecyclerView.Adapter<UserlistAdapter.UserInfoViewHolder>() {
    private var userInfoList = mutableListOf<UserInfo>()
    class UserInfoViewHolder(var binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserInfoViewHolder, position: Int) {
        var userInfo = userInfoList[position]
        Glide.with(holder.binding.imageView3.context)
            .load(userInfo.avatar)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(60, 60)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageView3)
        holder.binding.textView.text = userInfo.id.toString()
        holder.binding.textView2.text = userInfo.firstName
        holder.binding.textView3.text = userInfo.lastName
        holder.binding.textView4.text = userInfo.email
        holder.binding.user.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("key", userInfo)
            holder.binding.root.findNavController().navigate(R.id.userDetails, bundle)
        }
    }

    override fun getItemCount(): Int {
        return userInfoList.size
    }
    fun addlist(userInfolist: List<UserInfo>) {
        this.userInfoList.clear()
        this.userInfoList.addAll(userInfolist)
        notifyDataSetChanged()
    }
}