package com.fancy.instagram.ui.feed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fancy.instagram.base.BaseViewModel
import com.fancy.instagram.model.Feed
import com.fancy.instagram.network.InstagramRouter
import io.reactivex.schedulers.Schedulers

class FeedViewModel: BaseViewModel() {

    val feedListData: MutableLiveData<ArrayList<Feed>> = MutableLiveData() //에러메시지

    override fun onCreate() {

    }


    fun getFeedList() {
        InstagramRouter.api().getFeedList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe({
                feedListData.postValue(it.result)
            }, {
                Log.e("insta", "getFeedList error : ${it.localizedMessage}")
            }).addDisposable()
    }
}