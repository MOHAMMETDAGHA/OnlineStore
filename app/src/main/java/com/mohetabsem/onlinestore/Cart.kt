package com.mohetabsem.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.wtf
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.toolbar.*

class Cart : AppCompatActivity() {
    var cartItems:ArrayList<CartData>?=null
    var productsInCart:ArrayList<ProductItem>?=null
    var productsCount:ArrayList<Int>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        main_title.text="CART"
        cartItems= ArrayList()
        productsInCart= ArrayList()
        productsCount= ArrayList()
        // get cart item  from db
        val database = FirebaseDatabase.getInstance()
        val cartRef = database.getReference("cart")

        // start getting cart item
        cartRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "no zeft", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                Toast.makeText(applicationContext, " zeft", Toast.LENGTH_SHORT).show()
//                cartItems?.clear()
                productsInCart!!.clear()
                productsCount!!.clear()

                for (m in snapshot!!.children){
                    wtf("$$","there is an item")


                    // get from cart
                    var item=m.getValue(CartData::class.java)
                    cartItems?.add(item!!)
                    // add data to products array accerding 2 id
                    val prodRef = database.getReference("products").child(item?.id!!)
                    // get product according cart item
                    prodRef?.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(applicationContext, "no zeft", Toast.LENGTH_SHORT).show()
                        }
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var cItem = snapshot.getValue(ProductItem::class.java)
                            Toast.makeText(applicationContext, "dp", Toast.LENGTH_SHORT).show()
                            productsInCart?.add(cItem!!)
                            productsCount?.add(item?.count!!)

                        }
                    })
               }

                var add=CartAdapter(applicationContext, productsInCart!!,productsCount!!)
                add.notifyDataSetChanged()
                cart_list.adapter=add
                Log.wtf("size @@", "${productsInCart!!.count()} ")
                Func().calcAll(productsInCart!!, productsCount!!)
            }

        })

        // end getting cart item
        // get producaat all info

    }
}