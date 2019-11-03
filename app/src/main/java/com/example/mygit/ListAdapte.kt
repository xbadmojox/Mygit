package com.example.mygit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView



class ListAdapte(val context: Context, val list:ArrayList<Git>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false)
        val GitID = view.findViewById(R.id.Git_id) as AppCompatTextView
        val GitName = view.findViewById(R.id.Git_name) as AppCompatTextView
        val Gitdescription = view.findViewById(R.id.Git_description) as AppCompatTextView
        val Gitlogin = view.findViewById(R.id.Git_login) as AppCompatTextView
        val Giturl = view.findViewById(R.id.Git_url) as AppCompatTextView


        GitID.text = list[position].id.toString()
        GitName.text = list[position].name
        Gitdescription.text = list[position].description
        Gitlogin.text = list[position].login
        Giturl.text = list[position].url
        return view

    }


    override fun getItem(position: Int): Any {
      return list[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
       return list.size
    }
}