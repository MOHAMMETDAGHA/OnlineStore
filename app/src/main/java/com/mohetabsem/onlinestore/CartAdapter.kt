package com.mohetabsem.onlinestore

import android.content.Context
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_deatails.*
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.product.view.*
import kotlin.time.times

//class CartAdapter (context: Context, productList:ArrayList<ProductItem>,price:ArrayList<Double>):
//    ArrayAdapter<ProductItem>(context , 0,productList){

class CartAdapter(context: Context, productList: ArrayList<CartData>):
    ArrayAdapter<CartData>(context,0,productList ){
    var cartRef: DatabaseReference? =null
    var productData:ProductItem?=null

    val mContext: Context
    init {
        mContext=context

    }
    var cItem:CartData?=null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val cell = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false)
        cItem=getItem(position)
        var cartId=cItem?.id.toString()

        //get data drom db

        val database = FirebaseDatabase.getInstance()
        val prodRef = database.getReference("products").child(cartId)
        var item:ProductItem?=null

        prodRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(mContext, "no zeft", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                item = snapshot.getValue(ProductItem::class.java)
                var name =item?.name.toString()
                var price: Double? =item?.price
                var descount =item?.deiscount?.toDouble()
                var minus= descount?.div(100.0)?.let { price?.times(it) }
                var priceAfter= price?.minus(minus!!)
                var total:Double
                var count=cItem?.count?.toInt()
                cell.pName.text=name
                if(descount==0.0){
                    cell.pPrice.text=Func().getPrice(price!!,descount!!).toString()
                    cell.pPriceBefore.text=""
                    total= count!! * price!!
                }else{
                    cell.pPrice.text=Func().getPrice(price!!,descount!!).toString()
                    cell.pPriceBefore.text=price.toString()
                    total= count!! * priceAfter!!
                }
                cell.total.text=total.toString()
                //cell.pCount.text=count.toString()

            }
        })
        // we have data in variable item

//        val imageView=layout_inflator.prodImage
//        Picasso.get().load(item?.mainImg).into(imageView);

        return cell
    }
}