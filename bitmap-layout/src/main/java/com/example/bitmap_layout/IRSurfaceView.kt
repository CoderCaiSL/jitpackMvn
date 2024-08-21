package com.example.bitmap_layout

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceView
import android.view.View

/**
 * des:
 * author: CaiSongL
 * date: 2024/8/21 16:51
 **/
class IRSurfaceView : SurfaceView{

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}