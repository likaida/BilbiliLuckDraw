package com.aceli.bilibililuckdraw.fragment.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.FragmentThirdBinding
import com.aceli.bilibililuckdraw.widget.toasty.Toasty

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.jump.setOnClickListener {
            view.findNavController()
                .navigate(ThirdFragmentDirections.actionThirdFragmentToFirstFragment("3-1"))
        }
    }

    private fun initData() {
        val args = ThirdFragmentArgs.fromBundle(requireArguments())
        Toasty.show(args.posThird)
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
            ThirdFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}