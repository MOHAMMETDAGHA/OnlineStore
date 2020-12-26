package com.mohetabsem.onlinestore

class ProductItem{
    var id:String?=null
    var name:String?=null
    var description:String?=null
    var price:Double?=null
    var deiscount:Double?=null

    var mainImg:String?=null
    var images :ArrayList<String>?=null
    constructor(){

    }
    constructor(
        id:String,
        name:String,
        description: String,
        price:Double,
        mainImg:String,
        images :ArrayList<String>?){
        this.id=id
        this.name=name
        this.description=description
        this.price=price
        this.mainImg=mainImg
        this.images=images
        this.deiscount=0.0
    }

    constructor(
            id:String,
            name:String,
            description: String,
            price:Double,
            deiscount:Double,
            mainImg:String,
            images :ArrayList<String>?){
        this.id=id
        this.name=name
        this.description=description
        this.price=price
        this.deiscount=deiscount
        this.mainImg=mainImg
        this.images=images
    }
}