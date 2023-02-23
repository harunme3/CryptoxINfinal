package com.cryptoxin.data.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.UnfollowListBody
import com.cryptoxin.data.datasource.remotedata.UnfollowListI
import com.cryptoxin.data.models.unfollowlistm.UnfollowListData
import com.cryptoxin.pagging.UnFollowListSC
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UnfollowListR @Inject
constructor(
    private val unfollowListI: UnfollowListI,
) {
    fun getUnfollowStream(addressPrivateKeyBody: AddressPrivateKeyBody): Flow<PagingData<UnfollowListData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
             UnFollowListSC(unfollowListI, myAddress = addressPrivateKeyBody.myAddress, privateKey = addressPrivateKeyBody.privateKey)
            }
        ).flow
    }


}