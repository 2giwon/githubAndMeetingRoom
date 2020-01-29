package com.sample.egiwon.githubmeetingroom.ext

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

val listDayOfWeek = listOf("일", "월", "화", "수", "목", "금", "토")
fun LocalDateTime.getCurrentDate(): String =
    LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM월 dd일")) +
            " (${listDayOfWeek[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1]})" +
            " ⌵ "