package com.mohetabsem.onlinestore

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_deatails.*
import kotlinx.android.synthetic.main.activity_product_deatails.view.*
import kotlinx.android.synthetic.main.product.view.*
import kotlin.time.times

class ProductDeatails : AppCompatActivity() {
    var cartRef: DatabaseReference? =null
    var productData:ProductItem?=null
    var id :String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_deatails)
        priceBefore.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
        id=getIntent().extras?.getString("id")
        wtf("tocken id ","${id}")
    }

    override fun onStart() {
        super.onStart()
        val database = FirebaseDatabase.getInstance()
        val cartRef = database.getReference("cart")


        var data=productData
         wtf("final id","${id}")

        val prodRef = database.getReference("products").child(id.toString())
        var item:ProductItem?=null
        //
        prodRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "no zeft", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                item = snapshot.getValue(ProductItem::class.java)

                wtf("iss","${item?.name} -- ${item?.id}")
                wtf("TAG", "${item?.description} -- ${item?.id}")
                prodName.text=item?.name
                discription.text=item?.description

                prodName.text=item?.name
                if( item?.deiscount==0.0){ // if no discount
                    discount.setVisibility(View.INVISIBLE)
                    priceBefore.setVisibility(View.INVISIBLE)
                    priceAfter.text=item?.price.toString()
                }else{ // if there a discount
                    discount.text=" ${ item?.deiscount?.toInt().toString() }% OFF "
                    priceBefore.text=item?.price.toString()
                    var x = item?.deiscount?.div(100)?.let { item?.price?.times(it) }
                    priceAfter.text="${x?.let { item?.price?.minus(it) }}"
                }

                val layout_inflator = LayoutInflater.from(applicationContext).inflate(R.layout.activity_product_deatails,null)
                val imageView=layout_inflator.productImage
                Picasso.get().load("https://miro.medium.com/fit/c/250/166/1*gdW-KWbtWgCX8ipe4B-jYw.jpeg").into(imageView);
            }
        })

        cta_add2cart.setOnClickListener {
            cartRef!!.child(id.toString()).child("id")?.setValue(id.toString())
            cartRef!!.child("${id.toString()}").child("count")?.setValue(1)
        }
    }

//    fun post2cart(productId:String){
//        val value = cartRef!!.child(productId)?.setValue(true)
//    }
}