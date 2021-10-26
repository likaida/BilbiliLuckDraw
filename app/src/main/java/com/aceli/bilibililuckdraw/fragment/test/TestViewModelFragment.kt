package com.aceli.bilibililuckdraw.fragment.test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aceli.bilibililuckdraw.LuckDrawApplication
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.activity.TestAddWordActivity
import com.aceli.bilibililuckdraw.cell.CellWordItemViewBinder
import com.aceli.bilibililuckdraw.database.entity.WordEntity
import com.aceli.bilibililuckdraw.databinding.FragmentViewModelBinding
import com.aceli.bilibililuckdraw.viewmodel.WordViewModel
import com.aceli.bilibililuckdraw.viewmodel.WordViewModelFactory
import com.aceli.bilibililuckdraw.widget.multitype.MultiTypeAdapter
import com.aceli.bilibililuckdraw.widget.toasty.Toasty

class TestViewModelFragment : Fragment() {
    private lateinit var binding: FragmentViewModelBinding
    private var mData: MutableList<Any>? = ArrayList()
    private var mAdapter: MultiTypeAdapter? = MultiTypeAdapter()
    private lateinit var mActivity: Activity

    private val jumpAddWord = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val stringExtra = it.data?.getStringExtra(TestAddWordActivity.EXTRA_REPLY)
        wordViewModel.insert(WordEntity(stringExtra ?: ""))
        Toasty.show(stringExtra ?: "")
    }

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((activity?.application as LuckDrawApplication).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

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
        initView()
        initListener()
    }

    private fun initData() {
//        val arg = FirstFragmentArgs.fromBundle(requireArguments())
//        Toasty.show(arg.backUp)
    }

    private fun initView() {
        binding.mRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mAdapter?.register(CellWordItemViewBinder())
        (binding.mRecyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        binding.mRecyclerView.adapter = mAdapter
        mAdapter?.items = mData!!

        wordViewModel.allWords.observe(viewLifecycleOwner, {
            it.let {
                mData?.clear()
                mData?.addAll(it)
                mAdapter?.notifyDataSetChanged()
            }
        })
    }

    private fun initListener() {
        binding.fab.setOnClickListener {
            jumpAddWord.launch(Intent(mActivity, TestAddWordActivity::class.java))
        }
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