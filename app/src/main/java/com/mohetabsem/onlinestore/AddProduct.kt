package com.mohetabsem.onlinestore

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.add_url_daialog.view.*
import kotlinx.android.synthetic.main.toolbar.*


class AddProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        // Write a message to the database

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

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
    }
}