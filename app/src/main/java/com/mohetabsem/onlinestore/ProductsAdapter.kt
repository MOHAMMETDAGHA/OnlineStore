package com.mohetabsem.onlinestore

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_product.view.*
import kotlinx.android.synthetic.main.product.view.*


class ProductsAdapter (context: Context,productList:ArrayList<ProductItem>):
        ArrayAdapter<ProductItem>(context , 0,productList){
    val mContext:Context
    init {
        mContext=context
    }
    var item:ProductItem?=null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layout_inflator = LayoutInflater.from(context).inflate(R.layout.product,parent,false)
        val layout_inflator = LayoutInflater.from(context).inflate(R.layout.product,parent,false)
        item=getItem(position)
        layout_inflator.prodNameTxt.text=item?.name
        layout_inflator.prodPriceTxt.text=item?.price.toString()
        layout_inflator.discriptionTxt.text=item?.description
        val imageView=layout_inflator.prodImage
        Picasso.get().load(item?.mainImg).into(imageView);

        layout_inflator.add2cart.setOnClickListener {
            item=getItem(position)
            var intent=Intent(layout_inflator.context,ProductDeatails::class.java)
            wtf("pased id ","${item?.id}")
            intent.putExtra("id","${item?.id}")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent)
            //Toast.makeText(layout_inflator.context,"${getItem(position)?.name} ", Toast.LENGTH_LONG).show()

        }
        return layout_inflator
    }




}