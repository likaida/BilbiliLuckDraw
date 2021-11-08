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
import com.aceli.bilibililuckdraw.helper.CommentDataHelper
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.bean.JsonBean
import com.aceli.bilibililuckdraw.bean.VideoCommentBean
import com.aceli.bilibililuckdraw.cell.CellCommentFromNetItemViewBinder
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.databinding.FragmentCommentListBinding
import com.aceli.bilibililuckdraw.helper.GsonHelper
import com.aceli.bilibililuckdraw.helper.VideoDataManager
import com.aceli.bilibililuckdraw.widget.multitype.MultiTypeAdapter
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.google.gson.reflect.TypeToken
import com.gyf.immersionbar.ImmersionBar
import java.lang.Exception


class CommentListFragment : Fragment() {
    private lateinit var binding: FragmentCommentListBinding
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_comment_list, container, false)
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
        mAdapter?.register(CellCommentFromNetItemViewBinder())
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
                video.aid?.let {
                    idList = if (idList.isEmpty()) {
                        "$it"
                    } else {
                        "$idList,$it"
                    }
                }
            }
            val py: Python = Python.getInstance()
            py.getModule("GetComment").callAttr("init", idList)
            val pyObjectVideoInfo: PyObject = py.getModule("GetComment").callAttr("getJson")
            val jsonBean: JsonBean = pyObjectVideoInfo.toJava(
                JsonBean::class.java
            )
            var commentBean: ArrayList<VideoCommentBean>? = null
            try {
                commentBean = GsonHelper.instance.gson.fromJson(
                    jsonBean.jsonData,
                    object : TypeToken<ArrayList<VideoCommentBean>>() {}.type
                ) as ArrayList<VideoCommentBean>
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.mLoading.stop()
            if (!commentBean.isNullOrEmpty()) {
                CommentDataHelper.setData(commentBean)
                Toasty.success(
                    mActivity,
                    "Data was create by net"
                ).show()
                injectData()
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

    private fun injectData() {
        if (CommentDataHelper.commentsData?.isNullOrEmpty() == false) {
            var woman = 0
            var man = 0
            mData?.clear()
            CommentDataHelper.commentsData?.let {
                mData?.addAll(it)
                it.forEach { item ->
                    if (item.member?.sex == "男") {
                        man += 1
                    } else if (item.member?.sex == "女") {
                        woman += 1
                    }
                }
            }
            binding.mRepeatNum.text = CommentDataHelper.repeatData?.size?.toString() ?: "0"
            binding.mSuccessNum.text =
                CommentDataHelper.commentsData?.size?.toString() ?: "0"
            binding.mWomanNum.text = woman.toString()
            binding.mManNum.text = man.toString()
            mAdapter?.notifyDataSetChanged()
        } else {
            Toasty.error(
                mActivity,
                "Please create data before inject"
            ).show()
            return
        }
    }

    companion object {
        fun getInstance(): CommentListFragment {
            return CommentListFragment()
        }
    }

    interface OnChangeTabListener {
        fun onChangeTab(pos: Int)
    }
}