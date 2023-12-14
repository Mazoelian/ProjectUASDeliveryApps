package com.example.projectuas_restaurant.Domain

class CategoryDomain(var title: String, private var pic: String) {

    fun getTitleText(): String {
        return title
    }

    fun setTitleText(title: String) {
        this.title = title
    }

    fun getPic(): String {
        return pic
    }

    fun setPic(pic: String) {
        this.pic = pic
    }
}
