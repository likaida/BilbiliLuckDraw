package com.aceli.bilibililuckdraw.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aceli.bilibililuckdraw.bean.CommentBean
import com.aceli.bilibililuckdraw.helper.CommentDataHelper
import com.aceli.bilibililuckdraw.util.Utils
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.cell.CellCommentFromNetItemViewBinder
import com.aceli.bilibililuckdraw.databinding.FragmentDataManagerBinding
import com.aceli.bilibililuckdraw.widget.multitype.MultiTypeAdapter
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gyf.immersionbar.ImmersionBar
import java.lang.Exception


class DataManagerFragment : Fragment() {
    private lateinit var binding: FragmentDataManagerBinding
    private lateinit var mActivity: Activity
    private var mData: MutableList<Any>? = ArrayList()
    private var mAdapter: MultiTypeAdapter? = MultiTypeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_data_manager, container, false)
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
        binding.mInjectData.setOnClickListener {
            injectData()
        }
    }

    private fun createData() {
        if (binding.inputVideoId.text.isNullOrEmpty()) {
            Toasty.show("Please Enter Video ID")
            return
        }
        binding.mLoading.start()
        val fileName = "comment.json"
        val json = Utils.getJson(mActivity, fileName)
        val gson = Gson()
        var beanList: ArrayList<CommentBean>? = null
        try {
            beanList = gson.fromJson(
                json,
                object : TypeToken<ArrayList<CommentBean?>?>() {}.type
            ) as ArrayList<CommentBean>
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (beanList?.isNullOrEmpty() == false) {
            CommentDataHelper.setData(beanList)
            Handler().postDelayed({
                binding.mLoading.stop()
                Toasty.success(
                    mActivity,
                    "Data was create by net"
                ).show()
            }, 2000)
        } else {
            Toasty.error(
                mActivity,
                "Data was create error"
            ).show()
        }
    }

    private fun injectData() {
        if (CommentDataHelper.commentsData?.commentList?.isNullOrEmpty() == false) {
            mData?.clear()
            CommentDataHelper.commentsData?.commentList?.let {
                mData?.addAll(it)
            }
            binding.mRepeatNum.text = CommentDataHelper.repeatData?.size?.toString() ?: "0"
            binding.mSuccessNum.text =
                CommentDataHelper.commentsData?.commentList?.size?.toString() ?: "0"
            mAdapter?.notifyDataSetChanged()
        } else {
            Toasty.error(
                mActivity,
                "Please create data before inject"
            ).show()
            return
        }
    }
}