package com.aceli.bilibililuckdraw.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.activity.TestActivity
import com.aceli.bilibililuckdraw.activity.TestViewModelActivity
import com.aceli.bilibililuckdraw.bean.JsonBean
import com.aceli.bilibililuckdraw.bean.UserBean
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.databinding.FragmentSettingBinding
import com.aceli.bilibililuckdraw.helper.GsonHelper
import com.aceli.bilibililuckdraw.helper.VideoDataManager
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.google.gson.reflect.TypeToken
import com.gyf.immersionbar.ImmersionBar
import timber.log.Timber
import java.lang.Exception

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var mActivity: Activity
    private var user = UserBean("AceLi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
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
        binding.apply {
            mUser = user
            invalidateAll()
        }
    }

    private fun initView() {

    }

    private fun initListener() {
        binding.mJumpTesting.setOnClickListener {
            TestActivity.start(mActivity)
        }
        binding.mJumpViewModel.setOnClickListener {
            TestViewModelActivity.start(mActivity)
        }
        binding.mAddVideo.setOnClickListener {
            addVideo("BV1vQ4y1D7KJ")
        }
    }

    private fun addVideo(vid: String) {
        val py: Python = Python.getInstance()
        py.getModule("GetVideoInfo").callAttr("init", vid)
        val pyObjectVideoInfo: PyObject = py.getModule("GetVideoInfo").callAttr("getJson")
        val info: JsonBean = pyObjectVideoInfo.toJava(
            JsonBean::class.java
        )
        val aid = info.jsonData
        var infoBean: VideoInfoEntity? = null
        try {
            infoBean = GsonHelper.instance.gson.fromJson(
                info.jsonData,
                object : TypeToken<VideoInfoEntity>() {}.type
            ) as VideoInfoEntity
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (infoBean != null) {
            VideoDataManager.addVideo(infoBean)
            Toasty.success(mActivity, "Add Video ${infoBean.title} Success").show()
        } else {
            Toasty.error(mActivity, "Add Video $vid error").show()
        }
        Timber.d("python_likaida:aid->$aid")
    }

}