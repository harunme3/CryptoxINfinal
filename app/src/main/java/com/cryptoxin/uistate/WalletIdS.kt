package com.cryptoxin.uistate

import com.cryptoxin.data.datasource.roomdata.WalletEntity


sealed class WalletIdS {
    object Empty : WalletIdS()
    object Loading : WalletIdS()
    class Loaded(val data: WalletEntity) : WalletIdS()
    class Error(val message: String) : WalletIdS()
}