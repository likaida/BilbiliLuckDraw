package com.aceli.bilibililuckdraw.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.ActivityTestBinding
import com.aceli.bilibililuckdraw.databinding.ActivityTestViewModelBinding

class TestViewModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestViewModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_view_model)
    }

    companion object {
        fun start(context: Context) {
            Intent(context, TestViewModelActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}