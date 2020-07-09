package com.fancy.instagram.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
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

@BindingAdapter("bindFeedImage")
fun bindFeedImage(view: ImageView, contents: FeedContentsData?) {
    Glide.with(view.context)
        .load(contents?.url)
        .centerCrop()
        .into(view)
}

@BindingAdapter("bindProfileImage")
fun bindProfileImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .apply(RequestOptions.circleCropTransform())
        .into(view)
}