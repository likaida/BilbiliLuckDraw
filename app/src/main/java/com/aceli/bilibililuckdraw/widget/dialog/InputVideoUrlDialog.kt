package com.aceli.bilibililuckdraw.widget.dialog

import android.content.Context
import android.graphics.Rect
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DialerKeyListener
import android.view.Gravity
import android.view.KeyEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.aceli.bilibililuckdraw.R
import com.aceli.bilibililuckdraw.util.Utils.getColor
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import timber.log.Timber


class InputVideoUrlDialog(private val mContext: Context, theme: Int) : AppCompatDialog(
    mContext, theme
) {
    private var mOnTextSendListener: OnTextSendListener? = null
    private var imm: InputMethodManager? = null
    private var mDialogContentView: LinearLayout? = null
    private var mEditView: EditText? = null
    private var mSubmitBtn: TextView? = null
    private var mTestBtn: TextView? = null
    private var mLastDiff = 0
    private var testUrl =
        "https://www.bilibili.com/video/BV11L4y1B7a6?spm_id_from=333.999.0.0"

    private fun initView() {
        setContentView(R.layout.dialog_input_video_url)
        mEditView = findViewById(R.id.et_input_message)
        mDialogContentView = findViewById(R.id.mContentView)
        mSubmitBtn = findViewById(R.id.confirm_btn)
        mTestBtn = findViewById(R.id.mTestBtn)
        imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun initListener() {
        mEditView?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.isEmpty()) {
                    mSubmitBtn?.setTextColor(getColor(R.color.color_000000_alpha15))
                } else {
                    mSubmitBtn?.setTextColor(getColor(R.color.color_222))
                }
            }
        })

        mEditView?.setOnEditorActionListener { _, _, event ->
            when (event?.keyCode ?: 0) {
                KeyEvent.KEYCODE_ENDCALL, KeyEvent.KEYCODE_ENTER -> {
                    submit()
                    true
                }
                KeyEvent.KEYCODE_BACK -> {
                    dismiss()
                    false
                }
                else -> false
            }
        }
        mEditView?.keyListener = object : DialerKeyListener() {
            override fun getAcceptedChars(): CharArray {
                val a =
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+{}|:?\"<>/.,';\\][=-`"
                return a.toCharArray()
            }

            override fun getInputType(): Int {
                return InputType.TYPE_CLASS_TEXT
            }
        }
        mEditView?.setOnKeyListener { _, _, keyEvent ->
            Timber.d("onKey %s", keyEvent.keyCode)
            false
        }
        mSubmitBtn?.setOnClickListener {
            submit()
        }
        mTestBtn?.setOnClickListener {
            mEditView?.setText(testUrl)
        }
        mDialogContentView?.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            val r = Rect()
            //获取当前界面可视部分
            window?.decorView?.getWindowVisibleDisplayFrame(r)
            //获取屏幕的高度
            val screenHeight = window!!.decorView.rootView.height
            //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
            val heightDifference = screenHeight - r.bottom
            if (heightDifference <= 0 && mLastDiff > 0) {
                dismiss()
            }
            mLastDiff = heightDifference
        }
        mDialogContentView?.setOnClickListener {
        }
        setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.repeatCount == 0) {
                dismiss()
            }
            false
        }
    }

    private fun setLayout() {
        window?.setGravity(Gravity.BOTTOM)
        val p = window?.attributes
        p?.width = WindowManager.LayoutParams.MATCH_PARENT
        p?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = p
        setCancelable(true)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    fun setOnTextSendListener(onTextSendListener: OnTextSendListener?) {
        mOnTextSendListener = onTextSendListener
    }

    override fun dismiss() {
        super.dismiss()
        //dismiss之前重置mLastDiff值避免下次无法打开
        mLastDiff = 0
    }

    override fun show() {
        super.show()
        mEditView?.requestFocus()
    }

    init {
        this.window?.setWindowAnimations(R.style.main_menu_animstyle)
        initView()
        initListener()
        setLayout()
    }

    fun submit() {
        val text = mEditView?.text
        if (text.isNullOrEmpty()) {
            Toasty.error(mContext, "Please input video url").show()
        } else {
            if (text.contains("bilibil")) {
                val split = text.split("?")
                if (split.isNotEmpty()) {
                    val split1 = split[0].split("/")
                    if (split1.isNotEmpty()) {
                        mOnTextSendListener?.onTextSend(split1[split1.size - 1])
                        mEditView?.setText("")
                        dismiss()
                    } else {
                        showError()
                    }
                } else {
                    showError()
                }
            } else {
                showError()
            }
        }
    }

    private fun showError() {
        Toasty.error(mContext, "Please input correct url").show()
    }

    interface OnTextSendListener {
        fun onTextSend(msg: String?)
    }
}