package com.fancy.instagram.ui.feed

import android.graphics.Color
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fancy.instagram.R
import com.fancy.instagram.application.InstagramApp
import com.fancy.instagram.databinding.ItemFeedBinding
import com.fancy.instagram.model.Feed
import com.fancy.instagram.model.FeedContentsData

class FeedListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val feedList: ArrayList<Feed> = arrayListOf()

    inner class FeedViewHolder(val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Feed) {
            binding.apply {
                feed = data
                viewHolder = this@FeedViewHolder
                executePendingBindings()
            }
        }

        fun onFeedLike(id: Int, isLike: Boolean) {
            Log.d("test", "onFeedLike isLike : $id, isLike : $isLike")
            if (isLike) {
                binding.ivFeedLike.setImageDrawable(InstagramApp.drawable(R.drawable.ic_likes_border))
            } else {
                binding.ivFeedLike.setImageDrawable(InstagramApp.drawable(R.drawable.ic_likes_active))
            }
        }

        fun onFeedScrap(id: Int, isScrap: Boolean) {
            Log.d("test", "onFeedLike onFeedScrap : $id, isLike : $isScrap")
            if (isScrap) {
                binding.ivFeedLike.setImageDrawable(InstagramApp.drawable(R.drawable.baseline_bookmark_border_black_48))
            } else {
                binding.ivFeedLike.setImageDrawable(InstagramApp.drawable(R.drawable.baseline_bookmark_black_48))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedViewHolder(
            ItemFeedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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

@BindingAdapter("bind_feed_image")
fun bindFeedImage(view: ImageView, contents: FeedContentsData?) {
    Glide.with(view.context)
        .load(contents?.url)
        .centerCrop()
        .into(view)
}

@BindingAdapter("bind_profile_image")
fun bindProfileImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .apply(RequestOptions.circleCropTransform())
        .into(view)
}

@BindingAdapter("bind_feed_user_id", "bind_feed_user_name")
fun setFeedText(view: TextView, userId: String, userName: String) {
    val spannableString = SpannableString(view.text)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.linkColor = Color.BLACK
            ds.isUnderlineText = false
            ds.isFakeBoldText = true
        }
    }

    spannableString.setSpan(clickableSpan, 0, userName.length, 0)
    view.text = spannableString
    view.movementMethod = LinkMovementMethod.getInstance()
}