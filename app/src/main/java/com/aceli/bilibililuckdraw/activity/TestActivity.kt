package com.aceli.bilibililuckdraw.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
    }

    companion object {
        fun start(context: Context) {
            Intent(context, TestActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}