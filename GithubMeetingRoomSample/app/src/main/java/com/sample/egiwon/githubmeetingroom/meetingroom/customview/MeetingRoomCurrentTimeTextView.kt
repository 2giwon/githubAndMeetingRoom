package com.sample.egiwon.githubmeetingroom.meetingroom.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.ext.convertTimeToReserveTime
import java.math.BigDecimal

class MeetingRoomCurrentTimeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val timeWidth: BigDecimal by lazy { BigDecimal(measuredWidth / 9) }

    private lateinit var reservedMeetingRoom: MeetingRoom
    private var isSetPosition = false
    private var currentTimeX = 0.0f

    fun setReservedMeetingRoom(reservedMeetingRoom: MeetingRoom) {
        this.reservedMeetingRoom = reservedMeetingRoom
    }

    private val currentTimeIndicatorListeners = mutableListOf<CurrentTimeIndicatorListener>()

    fun addCurrentTimeIndicatorListener(listener: CurrentTimeIndicatorListener) {
        currentTimeIndicatorListeners.add(listener)
    }

    private fun getCurrentTimeToIndicatorTime(): Float =
        getCurrentTime().convertTimeToReserveTime()
            .toInt()
            .convertTimeToCount(measuredWidth)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!isSetPosition) {
            isSetPosition = true
            val moveX = (timeWidth * getCurrentTimeToIndicatorTime().toBigDecimal()).toFloat()
            val textWidth = paint.measureText(text.toString())

            x = when {
                moveX > 0 && moveX < measuredWidth -> moveX - (textWidth / 2)
                moveX >= measuredWidth -> (measuredWidth - textWidth)
                else -> moveX
            }

            currentTimeX = if (moveX > measuredWidth) measuredWidth.toFloat() else moveX
            currentTimeIndicatorListeners.forEach { it.onCurrentTimeTextViewMoved(currentTimeX) }

        }
    }
}