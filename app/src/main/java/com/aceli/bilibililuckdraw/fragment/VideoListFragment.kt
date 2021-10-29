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
import androidx.recyclerview.widget.LinearLayoutManager
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.cell.CellVideoInfoItemViewBinder
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.databinding.FragmentVideoListBinding
import com.aceli.bilibililuckdraw.helper.VideoDataManager
import com.aceli.bilibililuckdraw.widget.dialog.InputVideoUrlDialog
import com.aceli.bilibililuckdraw.widget.dialog.InputVideoUrlDialog.OnTextSendListener
import com.aceli.bilibililuckdraw.widget.multitype.MultiTypeAdapter
import com.aceli.bilibililuckdraw.widget.multitype.OnItemMultiClickListener


class VideoListFragment : Fragment(), OnItemMultiClickListener {
    private lateinit var binding: FragmentVideoListBinding
    private lateinit var mActivity: Activity
    private var mData: MutableList<Any>? = ArrayList()
    private var mAdapter: MultiTypeAdapter? = MultiTypeAdapter()
    private var mInputDialog: InputVideoUrlDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false)
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
        mInputDialog = InputVideoUrlDialog(mActivity, R.style.dialog_center)
        binding.mVideoRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter?.register(CellVideoInfoItemViewBinder(this))
        (binding.mVideoRecyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations =
            false
        binding.mVideoRecyclerView.adapter = mAdapter
        mAdapter?.items = mData!!
        val allVideo = VideoDataManager.getAllVideo()
        allVideo?.let {
            mData?.addAll(it)
            mAdapter?.notifyDataSetChanged()
        }
    }

    private fun initListener() {
        binding.mAddVideo.setOnClickListener {
            mInputDialog?.show()
        }
        mInputDialog?.setOnTextSendListener(object : OnTextSendListener {
            override fun onTextSend(msg: String?) {
                msg?.let {
                    VideoDataManager.addVideoById(it, object : VideoDataManager.OnAddVideoCallback {
                        override fun onAddVideoSuccess(videoInfo: VideoInfoEntity) {
                            refreshOrAddVideo(videoInfo)
                        }

                        override fun onAddVideoFail() {
                        }
                    })
                }
            }
        })
    }

    private fun refreshOrAddVideo(videoInfo: VideoInfoEntity) {
        mData?.forEachIndexed { index, video ->
            if (video is VideoInfoEntity && video.aid == videoInfo.aid) {
                mData?.set(index, videoInfo)
                mAdapter?.notifyItemChanged(index)
                return
            }
        }
        mData?.add(videoInfo)
        mAdapter?.notifyDataSetChanged()
    }

    override fun onBaseItemMultiClick(actionType: Int, pos: Int, ext: Any?) {
        TODO("Not yet implemented")
    }

}