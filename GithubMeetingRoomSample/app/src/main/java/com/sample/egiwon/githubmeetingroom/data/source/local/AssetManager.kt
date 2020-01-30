package com.sample.egiwon.githubmeetingroom.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import io.reactivex.Single
import java.nio.charset.Charset

class AssetManager(
    private val applicationContext: Context
) : AssetService {

    override fun getMeetingRooms(): Single<List<MeetingRoom>> =
        Single.fromCallable {
            val inputStream = applicationContext.assets.open("MUSINSA.json")
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()

            Gson().fromJson<List<MeetingRoom>>(
                String(buffer, Charset.defaultCharset()),
                object : TypeToken<List<MeetingRoom>>() {}.type
            )
        }

}