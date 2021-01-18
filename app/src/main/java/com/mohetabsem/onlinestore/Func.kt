package com.mohetabsem.onlinestore

import android.util.Log

class Func {
    fun getPrice(price:Double,discount:Double,count:Int): Array<Double> {

        var dis=price*((discount)/(100))
        var priceWdis=price-dis
        var total=priceWdis*count

        return arrayOf(price,priceWdis,total)
    }
    fun getItemPrice(price:Double,count:Int,discount:Double): Array<Double> {
        var dis=(price*((discount)/(100)))*count // حساب الخصم
        var subTotal=price*count // ✅ الاجمالي بدون خصم
        var priceWdis=subTotal-dis  // السعر مع الخصم
        var total=priceWdis

        return arrayOf(subTotal,dis,total)
    }
    fun calcAll(products:ArrayList<ProductItem>,count:ArrayList<Int>):Array<Double>{

        var subTotal=0.0
        var discount=0.0
        var total=0.0
        Log.wtf("size", "${products.size} -- ")
        for (m in count){
            Log.wtf("++++", "${m} ")

        }
        for (i in 0..products.size-1){
            var x =products[i]
            var y =count[i]
            var data =getItemPrice(x.price!!,y,x.deiscount!!)
            subTotal += data[0]
            discount += data[1]
            total += data[1]
            Log.wtf("input ###", "${x.price} -- ${y} ")
        }
        Log.wtf("prices ###", "${subTotal} -- ${total} ")

        return arrayOf(subTotal,discount,total)
    }
}