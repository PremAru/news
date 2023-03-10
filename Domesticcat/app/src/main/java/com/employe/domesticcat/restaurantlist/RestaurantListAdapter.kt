package com.employe.domesticcat.zomato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.employe.domesticcat.R
import com.employe.domesticcat.model.UserInfo
import kotlinx.android.synthetic.main.adapter_userinfo_list.view.*
import javax.inject.Inject

class UserInfoListAdapter @Inject constructor () : RecyclerView.Adapter<UserInfoListAdapter.RepositoryViewHolder>() {
    private var userInfoList: MutableList<UserInfo> = mutableListOf()
    var userInfoListClickListener: UserInfoListClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_userinfo_list, parent,
            false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userInfoList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        userInfoList[position]?.let { holder.bindFilter(it) }
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindFilter(userInfo: UserInfo) {
            itemView.userName.text = itemView.userName.context.getString(R.string.name) +
                    userInfo.name
            itemView.emailTextView.text = itemView.userName.context.getString(R.string.email) +
                    userInfo.email
            itemView.phoneTextView.text = itemView.userName.context.getString(R.string.phone) +
                    userInfo.phone
            itemView.idTextView.text = itemView.userName.context.getString(R.string.id)  +" "+
                    userInfo.id.toString()

            itemView.adapterConstraintLayout.setOnClickListener {
                val userInfo = userInfoList[adapterPosition]
                userInfoListClickListener?.userInfoListClicked(userInfo)
            }

        }

    }

    fun setUserInfoList(userInfosListData: List<UserInfo>) {
        val beforeSize = userInfoList.size
        userInfoList.addAll(userInfosListData)
        notifyItemRangeInserted(beforeSize, userInfoList.size)
    }

    fun setClickListener(listener: UserInfoListClickListener){
        userInfoListClickListener = listener;
    }
}