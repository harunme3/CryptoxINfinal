package com.cryptoxin.data.models.trxhistorym

data class Msg(
    val message: String,
    val result: List<Result>,
    val status: String
)