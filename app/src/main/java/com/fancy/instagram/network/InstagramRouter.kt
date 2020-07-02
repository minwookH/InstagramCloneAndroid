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

    interface InstagramAPI {
        @GET("api/post")
        fun getFeedList(): Flowable<FeedResponse>
    }
}