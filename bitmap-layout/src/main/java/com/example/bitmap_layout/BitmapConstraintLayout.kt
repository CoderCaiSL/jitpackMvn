package com.example.bitmap_layout

import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.drawToBitmap
import java.nio.ByteBuffer


/**
 * @author: CaiSongL
 * @date: 2023/6/21 17:13
 */
open class BitmapConstraintLayout : ConstraintLayout {
    var changeData = false
    var bitmap: Bitmap? = null

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    var bitmapTime = 0L
    var newPixels : ByteBuffer ?= null

    val viewBitmap: Bitmap?
        get() {
            if (changeData) {
                if (bitmap == null){
                    bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
                }
                var buf : ByteBuffer ?= null
                tmpBitmap?.let {
                    val bytes: Int = it.byteCount
                    val buf = ByteBuffer.allocate(bytes)
                    it.copyPixelsToBuffer(buf)
                    it.recycle()
                    tmpBitmap = null
                    buf?.position(0)
                    bitmap!!.copyPixelsFromBuffer(buf)
                }
                changeData = false
                Log.w("BitmapConstraintLayout","bitmap刷新：${System.currentTimeMillis() - bitmapTime}")
                bitmapTime = System.currentTimeMillis()
            }
//            val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager?
//            //最大分配内存
//            val memory = activityManager!!.memoryClass
//            println("memory: $memory")
//            val maxMemory = (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024)).toFloat()
//            val totalMemory = (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024)).toFloat()
//            //剩余内存
//            val freeMemory = (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024)).toFloat()
//            println("最大内存: $maxMemory")
//            println("总内存: $totalMemory")
//            println("空闲内存: $freeMemory")
            return bitmap
        }

    var tmpBitmap : Bitmap ?= null

    fun updateBitmap(){
        try {
            if (this.isShown){
                tmpBitmap = this.drawToBitmap()
            }
        }catch (e : Exception){

        }
    }

}