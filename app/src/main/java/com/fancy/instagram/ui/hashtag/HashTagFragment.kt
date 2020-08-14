package com.fancy.instagram.ui.hashtag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fancy.instagram.R
import com.fancy.instagram.databinding.FragmentFeedBinding
import com.fancy.instagram.databinding.FragmentHashtagBinding
import com.fancy.instagram.model.Feed
import com.fancy.instagram.model.FeedContentsData
import com.fancy.instagram.model.HashTag
import com.fancy.instagram.model.HashTagContentsData
import com.fancy.instagram.ui.feed.FeedViewModel
import kotlinx.android.synthetic.main.fragment_hashtag.*
import org.joda.time.DateTime

class HashTagFragment : Fragment() {

    lateinit var hashTagAdapter: HashTagListAdapter
    lateinit var feedViewModel: FeedViewModel
    lateinit var hashTagBinding: FragmentHashtagBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        hashTagBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hashtag, container, false)
        return hashTagBinding.root
    }

    private val feedListObserve = Observer<ArrayList<HashTagContentsData>> {
        hashTagAdapter.setList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hashTagData1 = HashTagContentsData("https://ojsfile.ohmynews.com/STD_IMG_FILE/2016/0516/IE001963941_STD.jpg", "image", "10")
        val hashTagData2 = HashTagContentsData("https://previews.123rf.com/images/4045qd/4045qd1608/4045qd160800044/61532164-%EA%B3%84%EB%A6%BC%EC%9D%98-%ED%92%8D%EA%B2%BD.jpg", "image", "11")
        val hashTagData3 = HashTagContentsData("https://cdn.crowdpic.net/detail-thumb/thumb_d_A175A8EE60E76F315D7C02F85C3B5D01.jpg", "image", "12")
        val hashTagData4 = HashTagContentsData("https://www.topstarnews.net/news/photo/202001/718730_431150_811.png", "image", "13")
        val hashTagData5 = HashTagContentsData("https://4.bp.blogspot.com/-Ndp9ucpPjY4/XTHqhPt6qRI/AAAAAAAAa_w/6pU95UzmhHw-Gr0dxj73J-Q_vKny1SsvACLcBGAs/s1600/1.jpg", "image", "14")

        val hashTag = HashTag(1, "1", "1", arrayListOf(hashTagData1, hashTagData2, hashTagData3), arrayListOf(hashTagData1, hashTagData2, hashTagData3, hashTagData4, hashTagData5), 1000, false)

        hashTagAdapter = HashTagListAdapter()
        // offline teset
        //hashTagAdapter.setList(list)
        hashTagBinding.hashtag = hashTag

        rv_hashtag_list.adapter = hashTagAdapter
        rv_hashtag_list.layoutManager = LinearLayoutManager(requireActivity())
        rv_hashtag_list.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )

        //feedViewModel = VMProviders.of(fragment = this).get(FeedViewModel::class.java)
        //feedViewModel.feedListData.observe(viewLifecycleOwner, feedListObserve)
        //feedViewModel.getFeedList()
    }

    @BindingAdapter("bind_hashtag_title_image")
    fun bindHashTagTitleImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .apply(RequestOptions.circleCropTransform())
            .into(view)
    }
}