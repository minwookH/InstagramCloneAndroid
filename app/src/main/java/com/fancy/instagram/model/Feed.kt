package com.fancy.instagram.model

data class Feed(
    val feedId: Int,
    val userId: Int,
    val userName: String,
    val userProfileImage: String,
    val feedContents: ArrayList<FeedContentsData>,
    val feedText: String,
    val likeCount: Int,
    val isLike: Boolean,
    val isScrap: Boolean,
    val date: String
)

data class FeedContentsData(
    val url: String,
    val type: String
)