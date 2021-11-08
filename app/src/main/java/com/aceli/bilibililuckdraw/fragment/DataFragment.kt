package com.aceli.bilibililuckdraw.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.bean.TabEntity
import com.aceli.bilibililuckdraw.databinding.FragmentDataBinding
import com.aceli.bilibililuckdraw.fragment.adapter.SimplePagerAdapter
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import java.util.ArrayList

class DataFragment : Fragment(), CommentListFragment.OnChangeTabListener {
    private lateinit var binding: FragmentDataBinding
    private lateinit var mActivity: Activity
    private var mFragments = mutableListOf<Fragment>()
    private var mPagerAdapter: SimplePagerAdapter? = null
    private val mTitles = arrayOf("Video", "Comment", "Barrage")
    private val mTabEntities = ArrayList<CustomTabEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    private fun initView() {
        mFragments.add(VideoListFragment())
        val dataManagerFragment = CommentListFragment.getInstance()
        dataManagerFragment.mCnChangeTabListener = this
        mFragments.add(dataManagerFragment)
        mFragments.add(BarrageFragment.getInstance())
        mPagerAdapter = SimplePagerAdapter(
            childFragmentManager,
            mFragments,
            mTitles
        )
        binding.viewpager.adapter = mPagerAdapter

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i]))
        }
        binding.tabLayout.setTabData(mTabEntities)
    }

    private fun initListener() {
        binding.tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                binding.viewpager.currentItem = position
            }

            override fun onTabReselect(position: Int) {}
        })
        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.tabLayout.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    override fun onChangeTab(pos: Int) {
        binding.viewpager.currentItem = pos
    }


}