package com.cryptoxin.uistate

import com.cryptoxin.data.models.fiftylevelrewardm.FiftyLevelRewardM


sealed class FiftyLevelRewardS {
    object Empty : FiftyLevelRewardS()
    object Loading : FiftyLevelRewardS()
    class Loaded(val data: FiftyLevelRewardM) : FiftyLevelRewardS()
    class Error(val message: String) : FiftyLevelRewardS()
}