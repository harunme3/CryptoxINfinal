package com.cryptoxin.uistate

import com.cryptoxin.data.models.bonusrewardm.BonusRewardM


sealed class BonusRewardS {
    object Empty : BonusRewardS()
    object Loading : BonusRewardS()
    class Loaded(val data: BonusRewardM) : BonusRewardS()
    class Error(val message: String) : BonusRewardS()
}