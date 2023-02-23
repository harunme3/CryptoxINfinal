package com.cryptoxin.uistate

import com.cryptoxin.data.models.bonustimem.BonusTimeM


sealed class BonusTimeS {
    object Empty : BonusTimeS()
    object Loading : BonusTimeS()
    class Loaded(val data: BonusTimeM) : BonusTimeS()
    class Error(val message: String) : BonusTimeS()
}