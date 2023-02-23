package com.cryptoxin.uistate

import com.cryptoxin.data.models.alluserm.AllUserM

sealed class AllUserS {
    object Empty : AllUserS()
    object Loading : AllUserS()
    class Loaded(val data: AllUserM) : AllUserS()
    class Error(val message: String) : AllUserS()
}