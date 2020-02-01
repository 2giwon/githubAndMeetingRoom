package com.sample.egiwon.githubmeetingroom.meetingroom

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sample.egiwon.githubmeetingroom.R
import com.sample.egiwon.githubmeetingroom.base.BaseRecyclerView
import com.sample.egiwon.githubmeetingroom.data.MeetingRoom
import com.sample.egiwon.githubmeetingroom.databinding.MeetingroomCardviewBinding
import com.sample.egiwon.githubmeetingroom.meetingroom.customview.CurrentTimeIndicatorListener

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
    ), CurrentTimeIndicatorListener {

        override fun bindItem(item: Any?) {
            (item as? MeetingRoom)?.run {
                with(binding) {
                    tvMeetingroomTitle.text = name
                    tvMeetingroomLocation.text = location

                    tvCurrentTime.addCurrentTimeIndicatorListener(this@MeetingRoomViewHolder)
                    tvCurrentTime.addCurrentTimeIndicatorListener(viewCurrentIndicator)
                    tvCurrentTime.setReservedMeetingRoom(this@run)
                    viewTimeBar.setReservedMeetingRoom(this@run)
                }

            }
        }

        override fun onCurrentTimeTextViewMoved(currentX: Float) {
            binding.viewTimeBar.currentTimeX = currentX
        }
    }
}