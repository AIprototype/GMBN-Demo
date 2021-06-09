package com.example.gmbn.utils

import android.view.View

interface ItemClickListener {
    fun onClick(
        view: View?,
        position: Int,
        isLongClick: Boolean
    )
}