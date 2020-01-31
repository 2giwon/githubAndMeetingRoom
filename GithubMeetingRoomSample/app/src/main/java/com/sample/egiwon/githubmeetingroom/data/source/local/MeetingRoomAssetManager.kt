package com.sample.egiwon.githubmeetingroom.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import io.reactivex.Single
import java.nio.charset.Charset

class MeetingRoomAssetManager(
    private val applicationContext: Context
) : MeetingRoomAssetService {

    override fun getMeetingRooms(): Single<List<MeetingRoom>> =
        Single.fromCallable {
            val inputStream = applicationContext.assets.open("MUSINSA.json")
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()

            val reservedMeetingRooms = Gson().fromJson<List<MeetingRoom>>(
                String(buffer, Charset.defaultCharset()),
                object : TypeToken<List<MeetingRoom>>() {}.type
            )

            createMeetingRooms(reservedMeetingRooms)
        }

    private fun createMeetingRooms(reservedMeetingRooms: List<MeetingRoom>): List<MeetingRoom> =
        getMeetingRoomTitles(applicationContext)
            .zip(getMeetingRoomLocations(applicationContext))
            .map { MeetingRoom(it.second, it.first, true, emptyList()) }
            .distinctSelectedLatter(reservedMeetingRooms)

    private fun Iterable<MeetingRoom>.distinctSelectedLatter(
        other: List<MeetingRoom>
    ): List<MeetingRoom> {
        val list = ArrayList<MeetingRoom>()
        mapIndexed { index, e ->
            var meetingRoom: MeetingRoom? = null
            other.forEach {
                if (it.name == e.name) {
                    meetingRoom = MeetingRoom(it.location, it.name, true, it.reservations)
                }
            }

            list.add(index, meetingRoom ?: MeetingRoom(e.location, e.name, true, emptyList()))
        }

        return list
    }

    private fun getMeetingRoomTitles(context: Context): List<String> =
        mutableListOf<String>().apply {
            (1..MAX_MEETING_ROOM).map { index ->
                add(context.getIdentifierString("meetingroom${index}_name"))
            }
        }

    private fun getMeetingRoomLocations(context: Context): List<String> =
        mutableListOf<String>().apply {
            (1..MAX_MEETING_ROOM).map { index ->
                add(context.getIdentifierString("meetingroom${index}_location_text"))
            }
        }

    private fun Context.getIdentifierString(resName: String): String =
        getString(resources.getIdentifier(resName, "string", packageName))

    companion object {
        private const val MAX_MEETING_ROOM = 6
    }

}