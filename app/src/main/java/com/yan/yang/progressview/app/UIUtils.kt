package com.yan.yang.progressview.app

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.DisplayMetrics
import android.util.Log
import android.widget.TextView

import java.lang.reflect.Field

/**
 * Created by YanYang on 2016/7/8.
 */
object UIUtils {

    var scale: Float = 0.toFloat()

    var densityDpi: Int = 0

    var fontScale: Float = 0.toFloat()

    var screenWidth: Int = 0
    var screenHeight: Int = 0

    fun init(context: Context) {
        val dm = context.resources.displayMetrics
        scale = dm.density
        densityDpi = dm.densityDpi
        fontScale = dm.scaledDensity
        if (dm.widthPixels < dm.heightPixels) {
            screenWidth = dm.widthPixels
            screenHeight = dm.heightPixels
        } else {
            screenWidth = dm.heightPixels
            screenHeight = dm.widthPixels
        }
        Log.e("screen", "屏幕宽度是:$screenWidth 高度是:$screenHeight dp:$scale fontScale:$fontScale")
    }

    fun setTextViewDrawableStart(tv: TextView, resId: Int) {

        tv.setCompoundDrawablesRelative(getBitmapDrawable(tv.context, resId), null, null, null)
    }

    fun setTextViewDrawableTop(tv: TextView, resId: Int) {
        tv.setCompoundDrawablesRelative(null, getBitmapDrawable(tv.context, resId), null, null)
    }

    fun getBitmapDrawable(context: Context, resId: Int): BitmapDrawable {
        val bitmap = BitmapFactory.decodeResource(context.resources, resId)
        val bd = BitmapDrawable(context.resources, bitmap)
        bd.setBounds(0, 0, bitmap.width, bitmap.height)
        return bd
    }


    /**
     * 根据指定的参数绘画一个圆弧
     */
    fun drawAnnular(canvas: Canvas, paint: Paint, x: Float,
                    y: Float, color: Int, radius: Float, startAngle: Float, angle: Float) {
        paint.color = color
        val rf1 = RectF(x - radius, y - radius, x + radius, y + radius)
        canvas.drawArc(rf1, startAngle, angle, false, paint)
    }


    /**
     * 判断两个点是否足够接近
     */
    fun closeEnough(cx: Float, cy: Float, x: Float, y: Float,
                    dis: Float): Boolean {
        return x < cx + dis && x > cx - dis && y < cy + dis && y > cy - dis
    }

    fun dpToPx(dp: Float): Int {
        return (dp * scale + 0.5f).toInt()
    }

    fun spToPx(sp: Float): Float {
        return sp * fontScale
    }


}
