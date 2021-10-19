package com.aceli.bilibililuckdraw.bean

import com.aceli.bilibililuckdraw.R
import com.flyco.tablayout.listener.CustomTabEntity

data class TabEntity(
    var title: String,
    var selectedIcon: Int = R.mipmap.ic_launcher,
    var unSelectedIcon: Int = R.mipmap.ic_launcher
) :
    CustomTabEntity {
    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}