package com.arlequins.zoco_1.ui.details.detailsNotification.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.databinding.CardViewMsgInItemBinding
import com.arlequins.zoco_1.databinding.CardViewMsgOutItemBinding
import com.arlequins.zoco_1.model.Chat

class MsgViewHolder(view: View ): RecyclerView.ViewHolder(view) {

    fun render(chatModel: Chat){
        if (chatModel.type == "out") {
            val outBinding = CardViewMsgOutItemBinding.bind(itemView)
            outBinding.msgOutTextView.text = chatModel.msg
            outBinding.dateMsgOutTextView.text = chatModel.date
        }
        else if(chatModel.type == "in") {
            val inBinding = CardViewMsgInItemBinding.bind(itemView)
            inBinding.msgInTextView.text = chatModel.msg
            inBinding.dateMsgInTextView.text = chatModel.date
        }
    }
}