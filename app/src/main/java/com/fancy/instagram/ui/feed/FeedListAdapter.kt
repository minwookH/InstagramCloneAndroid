package com.fancy.instagram.ui.feed

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.fancy.instagram.R
import com.fancy.instagram.model.Feed
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val feedList: ArrayList<Feed> = arrayListOf()

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(feed: Feed) {
            with(itemView) {
                Glide
                    .with(context)
                    .load(feed.userProfileImage)
                    .centerCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_feed_profile)

                Glide
                    .with(context)
                    .load(feed.feedImage)
                    .centerCrop()
                    .into(iv_feed_image)

                tv_feed_user_name.text = feed.userName

                tv_feed_recive_like.text = feed.likeCount.toString()

                tv_feed_contents.text = feed.contents
                //.placeholder("https://pbs.twimg.com/profile_images/549171896013438979/rqtU6Cvn_400x400.png")

                //iv_feed_profile
                //tv_feed_user_name
                //iv_feed_menu

                //iv_feed_image

                //iv_feed_like
                //iv_feed_reply
                //iv_feed_sharing

                //iv_feed_save

                //tv_feed_recive_like
                //tv_feed_contents_user_name
                //tv_feed_contents
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FeedViewHolder).onBind(feedList[position])
    }

    fun setList(list: List<Feed>) {
        feedList.addAll(list)
        notifyDataSetChanged()
    }
}