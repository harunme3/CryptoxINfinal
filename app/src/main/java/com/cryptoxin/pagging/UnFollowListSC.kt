package com.cryptoxin.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cryptoxin.data.bodymodel.UnfollowListBody
import com.cryptoxin.data.datasource.remotedata.UnfollowListI
import com.cryptoxin.data.models.unfollowlistm.UnfollowListData
import com.cryptoxin.data.repository.UnfollowListR



class UnFollowListSC(
    private val unfollowListI: UnfollowListI,
    private val  myAddress:String,
    private val privateKey: String
) : PagingSource<Int , UnfollowListData>() {

    override fun getRefreshKey(state: PagingState<Int , UnfollowListData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnfollowListData> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = unfollowListI.getUnfollowListI(UnfollowListBody(myAddress =myAddress , privateKey =privateKey , pages =nextPageNumber ))
            val data=response.body()!!.data
            val   prevKey = if(nextPageNumber==1) null else nextPageNumber-1
            //replace
            //Total number of Page I will be get in future totalnumberofpage
            val   nextKey = if(nextPageNumber==data.size) null else nextPageNumber+1
            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}