package com.cryptoxin.uistate


import com.cryptoxin.data.models.createaccountmodel.CreateAccount



sealed class CreateWalletState {
    object Empty : CreateWalletState()
    object Loading : CreateWalletState()
    class Loaded(val data: CreateAccount) : CreateWalletState()
    class Error(val message: String) : CreateWalletState()
}