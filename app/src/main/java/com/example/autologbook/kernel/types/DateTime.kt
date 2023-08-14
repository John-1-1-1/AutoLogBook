package com.example.autologbook.kernel.types

import java.util.Date

class DateTime(date: Date){
    var Year: Int = date.year + 1900
    var Month: Int = date.month
    var Day: Int = date.date
    var Hour: Int = date.hours
    var Minute: Int = date.minutes

    override fun toString(): String {
        return Day.toString() + "/" + Month.toString() + "/" +
                Day.toString() + " " + Hour.toString() + ":" + Minute.toString()
    }
}