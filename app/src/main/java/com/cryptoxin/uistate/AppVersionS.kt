package com.cryptoxin.uistate

import com.cryptoxin.data.models.appversionm.AppVersionM

sealed class AppVersionS {
    object Empty : AppVersionS()
    object Loading : AppVersionS()
    class Loaded(val data: AppVersionM) : AppVersionS()
    class Error(val message: String) : AppVersionS()
}