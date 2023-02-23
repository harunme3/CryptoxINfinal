package com.cryptoxin.uistate

import com.cryptoxin.data.models.allpostm.AllPostM

sealed class AllPostS {
    object Empty : AllPostS()
    object Loading : AllPostS()
    class Loaded(val data: AllPostM) : AllPostS()
    class Error(val message: String) : AllPostS()
}