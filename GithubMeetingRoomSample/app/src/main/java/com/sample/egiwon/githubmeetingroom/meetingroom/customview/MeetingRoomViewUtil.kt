package com.sample.egiwon.githubmeetingroom.meetingroom.customview

import java.time.LocalDateTime

fun Int.convertTimeToCount(measuredWidth: Int): Float =
    when {
        (this % 100 == 0 && this >= START_TIME && this < END_TIME) ->
            ((this - START_TIME) / 100).toFloat()
        (this < START_TIME) -> 0.0f
        (this >= END_TIME) -> measuredWidth.toFloat()
        else -> ((this - 930) / 100 + 0.45f)
    }

fun getCurrentTime(): LocalDateTime = CURRENT_TIME

private val CURRENT_TIME = LocalDateTime.now()
private const val START_TIME = 900
private const val END_TIME = 1800

