package com.aceli.bilibililuckdraw.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.ActivityTestAddWordBinding
import com.aceli.bilibililuckdraw.databinding.ActivityTestBinding
import com.aceli.bilibililuckdraw.databinding.ActivityTestViewModelBinding
import com.aceli.bilibililuckdraw.widget.toasty.Toasty

class TestAddWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestAddWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_add_word)
        initView()
        initListener()
    }

    private fun initView() {

    }

    private fun initListener() {
        binding.mAddButton.setOnClickListener {
            val text = binding.mEditText.text
            if (text.isNotEmpty()) {
                Intent().apply {
                    putExtra(EXTRA_REPLY, text.toString())
                    setResult(Activity.RESULT_OK, this)
                    finish()
                }
            } else {
                Toasty.error(this, "Please input word!!")
            }
        }
    }


    companion object {
        const val EXTRA_REPLY = "com.ace.REPLY"

        fun start(context: Context) {
            Intent(context, TestAddWordActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}