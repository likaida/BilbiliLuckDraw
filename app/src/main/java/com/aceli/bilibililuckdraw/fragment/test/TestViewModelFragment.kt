package com.aceli.bilibililuckdraw.fragment.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.FragmentViewModelBinding

class TestViewModelFragment : Fragment() {
    private lateinit var binding: FragmentViewModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_model, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()

    }

    private fun initData() {
//        val arg = FirstFragmentArgs.fromBundle(requireArguments())
//        Toasty.show(arg.backUp)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TestViewModelFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}