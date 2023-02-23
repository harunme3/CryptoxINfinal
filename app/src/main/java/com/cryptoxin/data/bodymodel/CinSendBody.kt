package com.cryptoxin.data.bodymodel


data class CinSendBody(
    val myAddress: String,
    val privateKey: String,
    val receiver: String,
    val amount: String,
)
