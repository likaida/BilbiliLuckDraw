package com.aceli.bilibililuckdraw.fragment.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.FragmentFirstBinding
import com.aceli.bilibililuckdraw.widget.toasty.Toasty

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.jump.setOnClickListener {
            view.findNavController()
                .navigate(FirstFragmentDirections.actionFirstFragmentToBlankFragment("1-2"))
        }
    }

    private fun initData() {
//        val arg = FirstFragmentArgs.fromBundle(requireArguments())
//        Toasty.show(arg.backUp)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}