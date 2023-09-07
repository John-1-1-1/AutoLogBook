package com.example.autologbook.kernel.types

class ItemsViewModel(textLarge: String, textSmaller: String, price: String) {
    var textViewLarge = ""
    var textViewSmall = ""
    var textViewPrice = ""

    init {
        textViewLarge = textLarge
        textViewSmall = textSmaller
        textViewPrice = price
    }
}
