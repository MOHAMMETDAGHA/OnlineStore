package com.mohetabsem.onlinestore

import android.content.Context
import android.graphics.Paint
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import coil.load
import coil.transform.CircleCropTransformation
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_deatails.*
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.product.view.*
import kotlin.time.times

//class CartAdapter (context: Context, productList:ArrayList<ProductItem>,price:ArrayList<Double>):
//    ArrayAdapter<ProductItem>(context , 0,productList){

class CartAdapter(context: Context,var productList: ArrayList<ProductItem>,var productCount:ArrayList<Int>):
    ArrayAdapter<ProductItem>(context,0,productList ){
    var cartRef: DatabaseReference? =null
    var productData:ProductItem?=null


    val mContext: Context
    init {
        mContext=context    

    }
    var cItem:ProductItem?=null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val cell = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false)
        cell.pPriceBefore.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
        cItem=getItem(position)
        var cartId=cItem?.id.toString()
        var prodCount: Int =productCount[position]
        //get data drom db

        val database = FirebaseDatabase.getInstance()
        val prodRef = database.getReference("products").child(cartId)
        val cartRef = database.getReference("cart")

        var item=getItem(position)
        var name =item?.name.toString()
        var price: Double? =item?.price
        var descount =item?.deiscount?.toDouble()
        var count=prodCount
        // put data in ui
        cell.pName.text=name
        cell.total.setText(Func().getPrice(price!!,descount!!,count)[2].toString())
        if(descount==0.0){
            cell.pPrice.text=price.toString()
            cell.pPriceBefore.text=""
        }else{
            cell.pPrice.text=Func().getPrice(price!!,descount!!,count)[1].toString()
            cell.pPriceBefore.text=price.toString()
        }
        cell.pCount.setText(count.toString())
        // we have data in variable item
        // when add item
        cell.add1.setOnClickListener {
            var x =count++
            productList.clear()
            productCount.clear()
            cartRef!!.child(cartId).child("count")?.setValue(count)
            //notifyDataSetChanged()

        }
        cell.del1.setOnClickListener {
            var x =count--
            productList.clear()
            productCount.clear()
            cartRef!!.child(cartId).child("count")?.setValue(count)
            //notifyDataSetChanged()

        }
        return cell
    }
}

//
//prodRef?.addValueEventListener(object : ValueEventListener {
//    override fun onCancelled(error: DatabaseError) {
//        Toast.makeText(mContext, "no zeft", Toast.LENGTH_SHORT).show()
//    }
//    override fun onDataChange(snapshot: DataSnapshot) {
//        // set evreything to variable
//
//        item = snapshot.getValue(ProductItem::class.java)
//        var name =item?.name.toString()
//        var price: Double? =item?.price
//        var descount =item?.deiscount?.toDouble()
//        var minus= descount?.div(100.0)?.let { price?.times(it) }
//        var priceAfter= price?.minus(minus!!)
//        var total:Double
//        var count=cItem?.count?.toInt()
//        var mainImage=item?.mainImg
//        // put data in UI
//        cell.pName.text=name
//        if(descount==0.0){
//            cell.pPrice.text=Func().getPrice(price!!,descount!!).toString()
//            cell.pPriceBefore.text=""
//            total= count!! * price!!
//        }else{
//            cell.pPrice.text=Func().getPrice(price!!,descount!!).toString()
//            cell.pPriceBefore.text=price.toString()
//            total= count!! * priceAfter!!
//        }
//        cell.total.text=total.toString()
//        cell.pCount.text=cItem?.count?.toString()
//        cell.pImage.load(mainImage){
//            crossfade(true)
//            placeholder(R.drawable.ic_baseline_crop_original_24)
//            transformations(CircleCropTransformation())
//        }
//
//
//        //cell.pCount.text=count.toString()
//
//
//    }
//})
