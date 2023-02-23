package com.cryptoxin.uistate

import com.cryptoxin.data.models.cinbalancem.CinBalanceM


sealed class CinBalanceS {
    object Empty : CinBalanceS()
    object Loading : CinBalanceS()
    class Loaded(val data: CinBalanceM) : CinBalanceS()
    class Error(val message: String) : CinBalanceS()
}