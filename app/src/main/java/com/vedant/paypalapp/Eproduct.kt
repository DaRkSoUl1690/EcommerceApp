package com.vedant.paypalapp

class Eproduct {

    var id: Int
    var name: String
    var price: Int

    constructor(id: Int, name: String, Price: Int, productPicture: Int) {
        this.id = id
        this.name = name
        this.price = Price
        this.productPicture = productPicture
    }

    var productPicture: Int


}
