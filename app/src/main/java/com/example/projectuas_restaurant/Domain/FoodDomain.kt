package com.example.projectuas_restaurant.Domain

import java.io.Serializable

class FoodDomain : Serializable {
    var title: String = ""
    var pic: String = ""
    var description: String = ""
    var fee: Double = 0.0
    var numberInCart: Int = 0

    constructor(title: String, pic: String, description: String, fee: Double) {
        this.title = title
        this.pic = pic
        this.description = description
        this.fee = fee
        this.numberInCart = 0
    }

    constructor(title: String, pic: String, description: String, fee: Double, numberInCart: Int) {
        this.title = title
        this.pic = pic
        this.description = description
        this.fee = fee
        this.numberInCart = numberInCart
    }

    // Fungsi yang tidak perlu di-deklarasikan secara eksplisit
    // Kotlin secara otomatis menyediakan metode getter dan setter untuk properti

    /*fun getNumberInCart(): Int {
        return numberInCart
    }*/

    /*fun setNumberInCart(numberInCart: Int) {
        this.numberInCart = numberInCart
    }*/
}
