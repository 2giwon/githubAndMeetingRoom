package com.sample.egiwon.githubmeetingroom.data

data class MeetingRoom(
    var location: String,
    var name: String,
    var available: Boolean,
    var reservations: List<Reservation>
)