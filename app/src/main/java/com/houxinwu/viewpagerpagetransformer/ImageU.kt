package com.houxinwu.viewpagerpagetransformer

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by houxinwu on 2017/7/21.
 */
fun ImageView.loadRes(resId: Int) {
    Glide.with(this.context).load(resId).into(this)
}