package com.mohetabsem.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.wtf
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.toolbar.*

class Cart : AppCompatActivity() {
    var cartItems:ArrayList<CartData>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        main_title.text="CART"
        cartItems= ArrayList()

        // get data from db
        val database = FirebaseDatabase.getInstance()
        val cartRef = database.getReference("cart")
        // start getting cart item
        cartRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "no zeft", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {


                for (m in snapshot!!.children){
                   //items?.clear()
                  var item=m.getValue(CartData::class.java)
                       cartItems?.add(item!!)
                       wtf("##","${item?.id}")
                       wtf("items","${cartItems?.get(0)}")

               }
                wtf("items","${cartItems}")
                cart_list.adapter=CartAdapter(applicationContext, cartItems!!)
            }
        })
        // end getting cart item
        wtf("items","cartItems}")
    }
}