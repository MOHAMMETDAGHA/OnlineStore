package com.mohetabsem.onlinestore

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter


class ProductsAdapter (context: Context):
        ArrayAdapter<String>(context , 0){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout_inflator = LayoutInflater.from(context).inflate(R.layout.product,parent,false)

        return layout_inflator
    }

    override fun getCount(): Int {
        return 5
    }



}