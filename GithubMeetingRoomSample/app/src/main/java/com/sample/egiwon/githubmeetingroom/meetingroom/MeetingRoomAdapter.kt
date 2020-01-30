package com.sample.egiwon.githubmeetingroom.meetingroom

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.databinding.MeetingroomCardviewBinding

class MeetingRoomAdapter(
    @LayoutRes private val layoutResId: Int = R.layout.meetingroom_cardview
) : BaseRecyclerView.Adapter<MeetingRoom, MeetingroomCardviewBinding>(
    layoutResId
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<MeetingroomCardviewBinding> = MeetingRoomViewHolder(parent)

    inner class MeetingRoomViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<MeetingroomCardviewBinding>(
        parent,
        layoutResId
    ) {

        override fun bindItem(item: Any?) {
            (item as? MeetingRoom)?.run {
                binding.tvMeetingroomTitle.text = name
                binding.tvMeetingroomLocation.text = location
            }
        }
    }
}