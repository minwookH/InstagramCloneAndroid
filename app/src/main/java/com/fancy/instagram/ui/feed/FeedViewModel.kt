package com.fancy.instagram.ui.feed

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fancy.instagram.application.InstagramApp
import com.fancy.instagram.base.BaseViewModel
import com.fancy.instagram.model.Feed
import com.fancy.instagram.network.InstagramRouter
import io.reactivex.schedulers.Schedulers

class FeedViewModel(application: InstagramApp): BaseViewModel(application) {

    val feedListData: MutableLiveData<ArrayList<Feed>> = MutableLiveData() //에러메시지

    override fun onCreate() {

    }

    fun getFeedList() {
        Log.d("insta", "getFeedList start")
        InstagramRouter.api().getFeedList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe({
                Log.d("insta", "$it")
                feedListData.postValue(it.result)
            }, {
                Log.e("insta", "getFeedList error : ${it.localizedMessage}")
            }).addDisposable()
    }
}