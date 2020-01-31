package com.sample.egiwon.githubmeetingroom.meetingroom.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import java.math.BigDecimal

class MeetingRoomReservationBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#e8e8e8")
    }

    private val timeWidth: BigDecimal by lazy { BigDecimal(measuredWidth / 9) }
    private lateinit var reservedMeetingRoom: MeetingRoom
    private val listRect = mutableListOf<Rect>()
    var currentTimeX = 0.0f

    fun setReservedMeetingRoom(reservedMeetingRoom: MeetingRoom) {
        this.reservedMeetingRoom = reservedMeetingRoom
    }

    private fun settingReservedTimeToBar() {
        reservedMeetingRoom.reservations.forEach {
            val convertStartTime = it.startTime
                .convertTimeToCount()
                .toBigDecimal()

            val convertEndTime = it.endTime
                .convertTimeToCount()
                .toBigDecimal()

            listRect.add(
                Rect(
                    (timeWidth * convertStartTime).toInt(),
                    0,
                    ((timeWidth * convertStartTime) +
                            (timeWidth * (convertEndTime - convertStartTime))).toInt(),
                    measuredHeight
                )
            )

        }
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

    private fun drawCurrentTimeReservedBar(canvas: Canvas?) {
        val rectF = RectF(
            0.0f,
            0.0f,
            currentTimeX,
            measuredHeight.toFloat()
        )

        canvas?.drawRect(rectF, paint)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawCurrentTimeReservedBar(canvas)
        settingReservedTimeToBar()
        listRect.forEach {
            canvas?.drawRect(it, paint)
        }
    }

}