package com.sizura.implicitappkotlin.helper

import java.text.SimpleDateFormat
import java.util.*

class Helper {
    //didalem companion object at kotlin agar bisa diaccess sama class2 lain
    companion object{

        fun currentDate(): String {
            val dateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
            val date = Date()
            return dateFormat.format(date)
        }
        const val CAMERA = 1
        const val GALERY = 2
        const val PHONE = 1

    }
}