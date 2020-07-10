package com.fancy.instagram.network

import com.fancy.instagram.network.response.FeedResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.*

class InstagramRouter : RetrofitCreator() {

    companion object {
        fun api(): InstagramAPI {
            return create(InstagramAPI::class.java)
        }
    }

    //또는 반환값을 Response<StartupResponse>
    interface InstagramAPI {
        @GET("api/feed")
        fun getFeedList(): Flowable<FeedResponse>

        @GET("api/feed/{id}")
        fun getFeed(@Path("id") id: Int): Flowable<FeedResponse>
    }
}