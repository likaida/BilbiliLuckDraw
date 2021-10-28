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
import com.aceli.bilibililuckdraw.databinding.FragmentVideoListBinding
import com.aceli.bilibililuckdraw.widget.dialog.InputVideoUrlDialog
import com.aceli.bilibililuckdraw.widget.dialog.InputVideoUrlDialog.OnTextSendListener
import com.aceli.bilibililuckdraw.widget.toasty.Toasty


class VideoListFragment : Fragment() {
    private lateinit var binding: FragmentVideoListBinding
    private lateinit var mActivity: Activity
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
        mInputDialog?.setOnTextSendListener(object : OnTextSendListener {
            override fun onTextSend(msg: String?) {
                Toasty.show(msg ?: "")
            }
        })
    }

    private fun initListener() {
        binding.mAddVideo.setOnClickListener {
            mInputDialog?.show()
        }
    }

}