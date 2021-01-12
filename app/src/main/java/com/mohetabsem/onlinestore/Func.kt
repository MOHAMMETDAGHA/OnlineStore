package com.mohetabsem.onlinestore

class Func {
    fun getPrice(price:Double,discount:Double,count:Int): Array<Double> {

        var after=price*((discount+100)/(100))
        var total=after*count
        return arrayOf(price,after,total)
    }
}