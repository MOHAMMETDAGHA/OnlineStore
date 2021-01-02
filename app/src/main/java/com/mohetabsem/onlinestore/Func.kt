package com.mohetabsem.onlinestore

class Func {
    fun getPrice(price:Double,discount:Double):Double{
        var after=price*((discount+100)/(100))
        return after
    }
}