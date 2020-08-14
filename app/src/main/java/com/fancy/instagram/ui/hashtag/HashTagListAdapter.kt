package com.fancy.instagram.ui.hashtag

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fancy.instagram.databinding.ItemHashtagBinding
import com.fancy.instagram.model.HashTagContentsData

class HashTagListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val hashTagList: ArrayList<HashTagContentsData> = arrayListOf()

    inner class HashTagViewHolder(val binding: ItemHashtagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HashTagContentsData) {
            binding.apply {
                hashTagContentsData = data
                viewHolder = this@HashTagViewHolder
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HashTagViewHolder(
            ItemHashtagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return hashTagList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HashTagViewHolder).onBind(hashTagList[position])
    }

    fun setList(list: List<HashTagContentsData>) {
        hashTagList.addAll(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter("bind_hashtag_image")
fun bindHashTagImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .into(view)
}