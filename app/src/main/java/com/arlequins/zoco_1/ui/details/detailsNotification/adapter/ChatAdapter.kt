package com.arlequins.zoco_1.ui.details.detailsNotification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.model.Chat
import kotlin.properties.Delegates

class ChatAdapter(
    private val chatList: ArrayList<Chat>,
    private val onItemClicked: (Chat) -> Unit
): RecyclerView.Adapter<MsgViewHolder>() {

    var positionType = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var layoutId by Delegates.notNull<Int>()
        layoutId = if ( chatList[positionType].type == "out"){
            R.layout.card_view_msg_out_item
        } else{
            R.layout.card_view_msg_in_item
        }
        return MsgViewHolder(layoutInflater.inflate(layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val item = chatList[position]
        positionType = position
        holder.render(item)
    }

    override fun getItemCount(): Int = chatList.size


}

