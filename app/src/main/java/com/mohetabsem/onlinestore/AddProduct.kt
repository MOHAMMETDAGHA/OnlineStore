package com.mohetabsem.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.add_url_daialog.view.*
import kotlinx.android.synthetic.main.toolbar.*

class AddProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        main_title.text="Add Products"
        var urls=ArrayList<String>()
        val builder=AlertDialog.Builder(this)
        val dailog_view = LayoutInflater.from(this).inflate(R.layout.add_url_daialog,null)
        builder.setView(dailog_view)
        addBu.setOnClickListener {
            val dialog=builder.create()
            dialog.show()
        }
        dailog_view.addUrl.setOnClickListener {
            if(dailog_view.urlEt.text.isNotEmpty()){
                urls.add(dailog_view.urlEt.text.toString())
            }
        }
    }
}