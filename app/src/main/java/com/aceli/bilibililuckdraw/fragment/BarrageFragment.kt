package com.aceli.bilibililuckdraw.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.bean.JsonBean
import com.aceli.bilibililuckdraw.bean.beans.BarrageBean
import com.aceli.bilibililuckdraw.cell.CellBarrageItemViewBinder
import com.aceli.bilibililuckdraw.const.ClickConst
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.databinding.FragmentBarrageBinding
import com.aceli.bilibililuckdraw.helper.GsonHelper
import com.aceli.bilibililuckdraw.helper.VideoDataManager
import com.aceli.bilibililuckdraw.widget.multitype.MultiTypeAdapter
import com.aceli.bilibililuckdraw.widget.multitype.OnItemMultiClickListener
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.google.gson.reflect.TypeToken
import com.gyf.immersionbar.ImmersionBar
import java.lang.Exception


class BarrageFragment : Fragment(), OnItemMultiClickListener {
    private lateinit var binding: FragmentBarrageBinding
    private lateinit var mActivity: Activity
    private var mData: MutableList<Any>? = ArrayList()
    private var mAdapter: MultiTypeAdapter? = MultiTypeAdapter()
    var mCnChangeTabListener: OnChangeTabListener? = null
    private var mVideoList: ArrayList<VideoInfoEntity>? = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_barrage, container, false)
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
        binding.mRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mAdapter?.register(CellBarrageItemViewBinder(this))
        (binding.mRecyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        binding.mRecyclerView.adapter = mAdapter
        mAdapter?.items = mData!!
    }

    private fun initListener() {
        binding.mCreateData.setOnClickListener {
            createData()
        }
    }

    private fun createData() {
        binding.mLoading.start()
        try {
            mVideoList = VideoDataManager.getAllVideo() as ArrayList<VideoInfoEntity>?
            if (mVideoList?.isNullOrEmpty() == true) {
                Toasty.show("Please Add Video")
                mCnChangeTabListener?.onChangeTab(0)
                return
            }
            Toasty.info(
                mActivity,
                "Please waiting for request data"
            ).show()
            var idList = ""
            mVideoList?.forEach { video ->
                video.bvid?.let {
                    idList = if (idList.isEmpty()) {
                        "$it"
                    } else {
                        "$idList,$it"
                    }
                }
            }
            val py: Python = Python.getInstance()
            py.getModule("GetDanmu").callAttr("init", idList)
            val pyObjectVideoInfo: PyObject = py.getModule("GetDanmu").callAttr("getJson")
            val jsonBean: JsonBean = pyObjectVideoInfo.toJava(
                JsonBean::class.java
            )
            var commentBean: BarrageBean? = null
            try {
                commentBean = GsonHelper.instance.gson.fromJson(
                    jsonBean.jsonData,
                    object : TypeToken<BarrageBean>() {}.type
                ) as BarrageBean
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.mLoading.stop()
            if (!commentBean?.barrageList.isNullOrEmpty()) {
                mData?.addAll(commentBean?.barrageList!!)
                Toasty.success(
                    mActivity,
                    "Data was create by net"
                ).show()
                mAdapter?.notifyDataSetChanged()
            } else {
                Toasty.error(
                    mActivity,
                    "Data was create error"
                ).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toasty.error(
                mActivity,
                "Data was create error"
            ).show()
            binding.mLoading.stop()
        }
    }

    companion object {
        fun getInstance(): BarrageFragment {
            return BarrageFragment()
        }
    }

    interface OnChangeTabListener {
        fun onChangeTab(pos: Int)
    }

    override fun onBaseItemMultiClick(actionType: Int, pos: Int, ext: Any?) {
        if (actionType == ClickConst.CLICK_ACTION_BARRAGE_NUM) {
            var num = 0
            mData?.forEach {
                if (it is String && ext is String && ext == it) {
                    num++
                }
            }
            Toasty.show("Barrage $ext num is $num")
        }
    }
}