package com.aceli.bilibililuckdraw.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.bean.TabEntity
import com.aceli.bilibililuckdraw.fragment.DataManagerFragment
import com.aceli.bilibililuckdraw.fragment.LuckDrawFragment
import com.aceli.bilibililuckdraw.fragment.SimplePagerAdapter
import com.aceli.bilibililuckdraw.util.Utils
import com.aceli.bilibililuckdraw.databinding.ActivityMainBinding
import com.aceli.bilibililuckdraw.fragment.SettingFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.gyf.immersionbar.ImmersionBar
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mFragments = mutableListOf<Fragment>()
    private var mPagerAdapter: SimplePagerAdapter? = null
    private var mLuckDrawFragment: LuckDrawFragment? = null

    private val mTitles = arrayOf("LuckDraw", "DataManager", "Setting")
    private val mTabEntities = ArrayList<CustomTabEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        initListener()
    }

    private fun initView() {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        binding.statusHeight.layoutParams.height = Utils.getStatusBarHeight(this)
        mLuckDrawFragment = LuckDrawFragment()
        mFragments.add(LuckDrawFragment())
        mFragments.add(DataManagerFragment())
        mFragments.add(SettingFragment())
        mPagerAdapter = SimplePagerAdapter(
            supportFragmentManager,
            mFragments,
            arrayOf("LuckDraw", "DataManager", "Setting")
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
        binding.viewpager.addOnPageChangeListener(object : OnPageChangeListener {
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
}