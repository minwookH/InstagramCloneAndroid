package com.fancy.instagram.model

data class HashTag(
    val hashTagId: Int,
    val userId: String,
    val hashTagName: String,
    val hashTagRecentContents: ArrayList<HashTagContentsData>,
    val hashTagPopularContents: ArrayList<HashTagContentsData>,
    val hashTagCount: Int,
    val isFollow: Boolean
)

data class HashTagContentsData(
    val url: String,
    val type: String,
    val feedId: String
)