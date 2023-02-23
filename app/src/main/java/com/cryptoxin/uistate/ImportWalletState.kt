package com.cryptoxin.uistate

import com.cryptoxin.data.models.importaccountmodel.ImportAccountModel


sealed class ImportWalletState {
    object Empty : ImportWalletState()
    object Loading : ImportWalletState()
    class Loaded(val data: ImportAccountModel) : ImportWalletState()
    class Error(val message: String) : ImportWalletState()
}