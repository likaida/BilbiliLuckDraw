package com.aceli.bilibililuckdraw.helper

import com.google.gson.Gson

class GsonHelper {
    val gson: Gson
        get() {
            if (Companion.gson == null) {
                synchronized(GsonHelper::class.java) {
                    if (Companion.gson == null) {
                        Companion.gson = Gson()
                    }
                }
            }
            return Companion.gson!!
        }

    companion object {
        private var INSTANCE: GsonHelper? = null
        private var gson: Gson? = null
        val instance: GsonHelper
            get() {
                if (INSTANCE == null) {
                    synchronized(GsonHelper::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE = GsonHelper()
                        }
                    }
                }
                return INSTANCE!!
            }
    }
}