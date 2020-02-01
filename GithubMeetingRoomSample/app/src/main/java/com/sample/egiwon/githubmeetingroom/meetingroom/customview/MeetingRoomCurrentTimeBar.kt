package com.sample.egiwon.githubmeetingroom.meetingroom.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class MeetingRoomCurrentTimeBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), CurrentTimeIndicatorListener {

    private var currentTimeX by Delegates.notNull<Float>()

    override fun onCurrentTimeTextViewMoved(currentX: Float) {
        currentTimeX = currentX
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        x = if (currentTimeX > 0) (currentTimeX - convertDpToPx(context, 1.0f)) else 0.0f
    }

    private fun convertDpToPx(context: Context, dp: Float): Float =
        dp * context.resources.displayMetrics.density
}