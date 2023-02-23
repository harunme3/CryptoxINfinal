package com.cryptoxin.data.models.createaccountmodel

data class SigningKey(
    val address: String,
    val keyPair: KeyPair,
    val mnemonic: String,
    val path: String,
    val privateKey: String,
    val publicKey: String
)