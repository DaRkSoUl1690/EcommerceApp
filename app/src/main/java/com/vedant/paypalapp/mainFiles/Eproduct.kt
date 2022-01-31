package com.vedant.paypalapp.mainFiles

class Eproduct {

    var id: Int
    var name: String

    constructor(id: Int, name: String, price: Int, picture: String) {
        this.id = id
        this.name = name
        this.price = price
        this.picture = picture
    }

    var price: Int
    var picture: String

}
