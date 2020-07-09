package com.fancy.instagram.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fancy.instagram.R
import com.fancy.instagram.application.InstagramApp
import com.fancy.instagram.base.BaseViewModelFactory
import com.fancy.instagram.base.VMProviders
import com.fancy.instagram.databinding.FragmentFeedBinding
import com.fancy.instagram.model.Feed
import com.fancy.instagram.model.FeedContentsData
import kotlinx.android.synthetic.main.fragment_feed.*
import org.joda.time.DateTime

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FeedFragment : Fragment() {

    lateinit var feedAdapter: FeedListAdapter
    lateinit var feedViewModel: FeedViewModel
    lateinit var feedBinding: FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        feedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        return feedBinding.root
        //return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    private val feedListObserve = Observer<ArrayList<Feed>> {
        feedAdapter.setList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feed1 = Feed(
            1,
            "test",
            "Test11",
            "https://www.topstarnews.net/news/photo/202001/718730_431150_811.png",
            arrayListOf(
                FeedContentsData(
                    "https://cdn.crowdpic.net/detail-thumb/thumb_d_A175A8EE60E76F315D7C02F85C3B5D01.jpg",
                    "image"
                )
            ),
            "Contents11",
            1,
            false,
            false,
            DateTime.now().toString("yyyy-MM-dd")
        )

        val feed2 = Feed(
            2,
            "teset2",
            "Test22",
            "https://4.bp.blogspot.com/-Ndp9ucpPjY4/XTHqhPt6qRI/AAAAAAAAa_w/6pU95UzmhHw-Gr0dxj73J-Q_vKny1SsvACLcBGAs/s1600/1.jpg",
            arrayListOf(
                FeedContentsData(
                    "https://previews.123rf.com/images/4045qd/4045qd1608/4045qd160800044/61532164-%EA%B3%84%EB%A6%BC%EC%9D%98-%ED%92%8D%EA%B2%BD.jpg",
                    "image"
                )
            ),
            "Contents22",
            2,
            false,
            false,
            DateTime.now().toString("yyyy-MM-dd")
        )

        val feed3 = Feed(
            3,
            "teset3",
            "Test33",
            "https://www.topstarnews.net/news/photo/202001/718730_431150_811.png",
            arrayListOf(
                FeedContentsData(
                    "https://ojsfile.ohmynews.com/STD_IMG_FILE/2016/0516/IE001963941_STD.jpg",
                    "image"
                )
            ),
            "Contents33",
            3,
            false,
            false,
            DateTime.now().toString("yyyy-MM-dd")
        )

        val list = arrayListOf(feed1, feed2, feed3)

        feedAdapter = FeedListAdapter()
        // offline teset
        //feedAdapter.setList(list)
        rv_feed_list.adapter = feedAdapter
        rv_feed_list.layoutManager = LinearLayoutManager(requireActivity())
        rv_feed_list.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )

        feedViewModel = VMProviders.of(fragment = this).get(FeedViewModel::class.java)
        feedViewModel.feedListData.observe(viewLifecycleOwner, feedListObserve)
        feedViewModel.getFeedList()
    }
}