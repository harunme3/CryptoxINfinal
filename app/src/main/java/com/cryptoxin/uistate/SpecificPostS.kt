package com.cryptoxin.uistate


import com.cryptoxin.data.models.specificpostm.SpecificPostM


sealed class SpecificPostS {
    object Empty : SpecificPostS()
    object Loading : SpecificPostS()
    class Loaded(val data: SpecificPostM) : SpecificPostS()
    class Error(val message: String) : SpecificPostS()
}