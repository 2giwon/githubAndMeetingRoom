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
            val convertedStartTime = it.startTime
                .convertTimeToCount(measuredWidth)
                .toBigDecimal()

            val convertedEndTime = it.endTime
                .convertTimeToCount(measuredWidth)
                .toBigDecimal()

            listRect.add(
                Rect(
                    (timeWidth * convertedStartTime).toInt(),
                    0,
                    ((timeWidth * convertedStartTime) +
                            (timeWidth * (convertedEndTime - convertedStartTime))).toInt(),
                    measuredHeight
                )
            )

        }
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