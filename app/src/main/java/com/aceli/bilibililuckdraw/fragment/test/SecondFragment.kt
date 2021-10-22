package com.aceli.bilibililuckdraw.fragment.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.FragmentSecondBinding
import com.aceli.bilibililuckdraw.widget.toasty.Toasty

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()

        binding.jump.setOnClickListener {
            view.findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToThirdFragment("2-3"))
        }
    }

    private fun initData() {
        val args = SecondFragmentArgs.fromBundle(requireArguments())
        Toasty.show(args.posSecond)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}