package com.sample.egiwon.githubmeetingroom.meetingroom.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.ext.convertTimeToReserveTime
import java.math.BigDecimal
import java.time.LocalDateTime

class MeetingRoomCurrentTimeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val timeWidth: BigDecimal by lazy { BigDecimal(measuredWidth / 9) }

    private lateinit var reservedMeetingRoom: MeetingRoom
    private var isSetPosition = false
    var currentTimeX = 0.0f
        private set

    fun setReservedMeetingRoom(reservedMeetingRoom: MeetingRoom) {
        this.reservedMeetingRoom = reservedMeetingRoom
    }

    private val currentTimeIndicatorListeners = mutableListOf<CurrentTimeIndicatorListener>()

    fun addCurrentTimeIndicatorListener(listener: CurrentTimeIndicatorListener) {
        currentTimeIndicatorListeners.add(listener)
    }

    private fun String.convertTimeToCount(): Float =
        when (this) {
            "0900" -> 0.0f
            "0930" -> 0.45f
            "1000" -> 1.0f
            "1030" -> 1.45f
            "1100" -> 2.0f
            "1130" -> 2.45f
            "1200" -> 3.0f
            "1230" -> 3.45f
            "1300" -> 4.0f
            "1330" -> 4.45f
            "1400" -> 5.0f
            "1430" -> 5.45f
            "1500" -> 6.0f
            "1530" -> 6.45f
            "1600" -> 7.0f
            "1630" -> 7.45f
            "1700" -> 8.0f
            "1730" -> 8.45f
            else -> measuredWidth.toFloat()
        }

    private fun getCurrentTimeToIndicatorTime(): Float =
        LocalDateTime.now().convertTimeToReserveTime()
            .convertTimeToCount()

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
            currentTimeIndicatorListeners.forEach { it.onCurrentTimeMoved(currentTimeX) }

        }
    }
}