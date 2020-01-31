package com.sample.egiwon.githubmeetingroom.meetingroom

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.databinding.ItemAvailableMeetingRoomBinding

class AvailableMeetingRoomAdapter(
    @LayoutRes private val layoutResId: Int = R.layout.item_available_meeting_room
) : BaseRecyclerView.Adapter<MeetingRoom, ItemAvailableMeetingRoomBinding>(
    layoutResId
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemAvailableMeetingRoomBinding> =
        AvailableMeetingRoomViewHolder(parent)

    inner class AvailableMeetingRoomViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemAvailableMeetingRoomBinding>(
        parent,
        layoutResId
    ) {
        override fun bindItem(item: Any?) {
            (item as? MeetingRoom)?.run {
                if (available) binding.btnMeetingRoom.text = name
            }
        }
    }
}