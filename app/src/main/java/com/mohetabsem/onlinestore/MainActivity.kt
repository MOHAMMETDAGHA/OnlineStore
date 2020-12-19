package com.mohetabsem.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.mohetabsem.onlinestore.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var myRef: DatabaseReference? =null
    var mProductItems :ArrayList<ProductItem>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        product_gv.adapter=ProductsAdapter(this)

    }

    override fun onStart() {
        super.onStart()
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("products")
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "no zeft", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {

                mProductItems?.clear()
                for (m in snapshot!!.children ){ // add keys
                    var todo =m.getValue(ProductItem::class.java)
                    mProductItems?.add(0,todo!!)
                    //wtf("++","${m.getValue(Todo::class.java)}")
                }
                var a = ProductsAdapter(applicationContext/*, mTodo!!*/)
                product_gv.adapter=ProductsAdapter(applicationContext)

            }
        })
    }
}