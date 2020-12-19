package com.mohetabsem.onlinestore

import android.os.Bundle
import android.util.Log.wtf
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.add_url_daialog.view.*
import kotlinx.android.synthetic.main.toolbar.*


class AddProduct : AppCompatActivity() {
    var myRef: DatabaseReference? =null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        // db declaration

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("products")

        myRef.setValue("Hello, World!")
        main_title.text="Add Products"
        var urls=ArrayList<String>()
        val builder=AlertDialog.Builder(this)
        val dailog_view = LayoutInflater.from(this).inflate(R.layout.add_url_daialog,null)
        builder.setView(dailog_view)
        val dialog=builder.create()
        addBu.setOnClickListener {
            dialog.show()
        }
        dailog_view.addUrl.setOnClickListener {
            if(dailog_view.urlEt.text.isNotEmpty()){
                urls.add(dailog_view.urlEt.text.toString())
                val urlAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,urls)
                url_list.adapter=urlAdapter
                dailog_view.urlEt.setText("")
            }
        }
        dailog_view.cancle.setOnClickListener {
            dialog.dismiss()
            dailog_view.urlEt.setText("")
        }
        cta_add2db.setOnClickListener {
            if(productDesc.text.isNotEmpty() ||productName.text.isNotEmpty() ||productPrict.text.isNotEmpty()||mainUrl.text.isNotEmpty() ) {
                var id = myRef!!.push().key.toString()
                var name = productName.text.toString()
                var description = productDesc.text.toString()
                var price = productPrict.text.toString().toDouble()
                var mainUrl = mainUrl.text.toString()
                var mProduct = ProductItem(id, name, "description", price, mainUrl, urls)
                wtf("@@", "$urls")
                myRef!!.child(id)?.setValue(mProduct)
                
            }else{
                Toast.makeText(this,"no shismo ",Toast.LENGTH_LONG).show()
            }
            // post2dp("id","name","description",5.5,"mainUrl",urls)
        }
    }
    fun post2dp( id:String,
                 name:String,
                 description: String,
                 price:Double,
                 mainImg:String,
                 images :ArrayList<String>){
        var mProduct=ProductItem(id,name,description,price,mainImg, images)
        wtf("@@","${mProduct.id}")
        myRef!!.child(id)?.setValue(mProduct)
    }

}