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
     var items:ArrayList<CartData>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        main_title.text="CART"

        var addapter:ArrayList<String>
        addapter= arrayListOf("oooo","ddd")


        // get data from db
        val database = FirebaseDatabase.getInstance()
        val cartRef = database.getReference("cart")
        // start getting cart item
        cartRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "no zeft", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
               for (m in snapshot.children){
                   items?.clear()
                  var item=m.getValue(CartData::class.java)
                   if (item != null) {
                       items?.add(0,item)
                       wtf("##","${item.count}")
                   }else{
                       Toast.makeText(applicationContext,"null item found",Toast.LENGTH_LONG).show()
                   }
               }
            }
        })
        // end getting cart item
        cart_list.adapter= items?.let { CartAdapter(this, it) }
    }
}