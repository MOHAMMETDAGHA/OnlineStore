package com.mohetabsem.onlinestore

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product.view.*

//class CartAdapter (context: Context, productList:ArrayList<ProductItem>,price:ArrayList<Double>):
//    ArrayAdapter<ProductItem>(context , 0,productList){

class CartAdapter (context: Context,productList:ArrayList<CartData>):
    ArrayAdapter<CartData>(context,0,productList ){

    val mContext: Context
    init {
        mContext=context
    }
    var item:CartData?=null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val cell = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false)
        item=getItem(position)
        cell.
//        val imageView=layout_inflator.prodImage
//        Picasso.get().load(item?.mainImg).into(imageView);

        return cell
    }

    override fun getCount(): Int {
        return 5
    }




}