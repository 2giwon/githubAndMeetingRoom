package com.sample.egiwon.githubmeetingroom.meetingroom.customview

fun String.convertTimeToCount(measuredWidth: Int): Float =
    when {
        (toInt() % 100 == 0 && toInt() >= START_TIME && toInt() < END_TIME) ->
            ((toInt() - START_TIME) / 100).toFloat()
        (toInt() < START_TIME) -> 0.0f
        (toInt() >= END_TIME) -> measuredWidth.toFloat()
        else -> ((toInt() - 930) / 100 + 0.45f)
    }


private const val START_TIME = 900
private const val END_TIME = 1800

