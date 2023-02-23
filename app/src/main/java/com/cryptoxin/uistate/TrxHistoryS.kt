package com.cryptoxin.uistate

import com.cryptoxin.data.models.specificpostm.SpecificPostM
import com.cryptoxin.data.models.trxhistorym.TrxHistoryM


sealed class TrxHistoryS {
    object Empty : TrxHistoryS()
    object Loading : TrxHistoryS()
    class Loaded(val data: TrxHistoryM) : TrxHistoryS()
    class Error(val message: String) : TrxHistoryS()
}