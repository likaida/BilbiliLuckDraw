package com.aceli.bilibililuckdraw.fragment

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.FragmentLuckDrawBinding
import com.aceli.bilibililuckdraw.helper.CommentDataHelper
import com.aceli.bilibililuckdraw.util.Utils
import com.aceli.bilibililuckdraw.util.gone
import com.aceli.bilibililuckdraw.util.visible
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.gyf.immersionbar.ImmersionBar
import kotlin.random.Random

class LuckDrawFragment : Fragment() {
    private lateinit var binding: FragmentLuckDrawBinding
    private lateinit var mActivity: Activity
    private var mHandler: Handler = Handler()
    private var isLooping = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_luck_draw, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initListener()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    private fun initData() {

    }

    private fun initView() {
        binding.mIcon.setImageURI("https://img1.baidu.com/it/u=3122136587,3938996930&fm=26&fmt=auto")
    }

    private fun initListener() {
        binding.mStartLuck.setOnClickListener {
            if (isLooping) {
                stopLoop()
            } else {
                startLuck()
            }
        }
    }

    private fun startLuck() {
        if (CommentDataHelper.commentsData?.commentList?.isNullOrEmpty() == false) {
            Toasty.info(mActivity, "Come on .....").show()
            binding.mStartLuck.text = "Stop"
            isLooping = true
            startLoop()
        } else {
            Toasty.error(mActivity, "Please create or inject data before luck draw").show()
        }
    }

    private fun startLoop() {
        val size = CommentDataHelper.commentsData?.commentList?.size ?: 0
        val nextInt = Random.nextInt(size)
        val get = CommentDataHelper.commentsData?.commentList?.get(nextInt)
        binding.mContentView.gone()
        get.let {
            binding.mUserName.text = it?.member?.uname
            binding.mContent.text = it?.content?.message
            binding.mCreateTime.text = Utils.convertTimeStampForCreateTime((it?.ctime ?: 0) * 1000)
        }
        mHandler.postDelayed(mRunnable, 100)
    }

    private fun stopLoop() {
        binding.mStartLuck.text = "Start"
        mHandler.removeCallbacks(mRunnable)
        binding.mContentView.visible()
        isLooping = false
    }

    private var mRunnable = Runnable {
        startLoop()
    }
}