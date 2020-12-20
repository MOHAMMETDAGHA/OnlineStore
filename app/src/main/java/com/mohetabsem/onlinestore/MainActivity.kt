package com.mohetabsem.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.database.*
import com.mohetabsem.onlinestore.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product.view.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {
    var myRef: DatabaseReference? =null
    var mProductItems :ArrayList<ProductItem>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProductItems = ArrayList()
        //product_gv.adapter=ProductsAdapter(this)

        mnue.setOnClickListener {
            var intent=Intent(this,AddProduct::class.java)
            startActivity(intent)
        }

//        product_gv.add2cart.setOnItemClickListener { parent, view, position, id ->
//            Toast.makeText(this,"#${product_gv.getItemAtPosition(position)} ",Toast.LENGTH_LONG).show()
//
//
//        }
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
                Toast.makeText(applicationContext, "db", Toast.LENGTH_SHORT).show()

                mProductItems?.clear()
                for (m in snapshot!!.children ){ // add keys
                    var item =m.getValue(ProductItem::class.java)
                    mProductItems?.add(0,item!!)
                    //wtf("++","${m.getValue(Todo::class.java)}")
                }
//                var a = ProductsAdapter(applicationContext/*, mTodo!!*/)
                product_gv.adapter=ProductsAdapter(applicationContext,mProductItems!!)

            }
        })
    }
}