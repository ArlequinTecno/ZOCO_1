package com.arlequins.zoco_1.ui.details.detailsNotification

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.CardViewMsgInItemBinding
import com.arlequins.zoco_1.databinding.CardViewMsgOutItemBinding
import com.arlequins.zoco_1.model.Chat
import kotlin.properties.Delegates

class DetailNotificationAdapter(
    private val detailsNotificationList: MutableList<Chat?>,
    private val onItemClicked: (Chat) -> Unit
    ): RecyclerView.Adapter<DetailNotificationAdapter.DetailNotificationViewHolder>() {


    private var positionType = 0

    override fun onBindViewHolder(holder: DetailNotificationViewHolder, position: Int) {
        val msg = detailsNotificationList[position]
        msg?.let { holder.bind(it) }
        holder.itemView.setOnClickListener { detailsNotificationList[position]?.let { it1 ->
            onItemClicked(it1)
        } }
    }

    override fun getItemCount(): Int = detailsNotificationList.size

    fun appendItems(newList: ArrayList<Chat>) {
        detailsNotificationList.clear()
        detailsNotificationList.addAll(newList)
        notifyDataSetChanged()
    }
    fun appendItem(newItem: Chat){
        detailsNotificationList.add(newItem)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailNotificationViewHolder {
        val msgType = detailsNotificationList[positionType]?.type
        Log.i("adapter", msgType.toString())
        var layoutId by Delegates.notNull<Int>()

        layoutId = if (msgType.toString() == "out"){
            R.layout.card_view_msg_out_item
        } else{
            R.layout.card_view_msg_in_item
        }
        positionType += 1
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return  DetailNotificationViewHolder(view)
    }

    class DetailNotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(chat: Chat) {

            if (chat.type == "out") {
                val outBinding = CardViewMsgOutItemBinding.bind(itemView)
                outBinding.msgOutTextView.text = chat.msg
                outBinding.dateMsgOutTextView.text = chat.date
            }
            else if(chat.type == "in") {
                val inBinding = CardViewMsgInItemBinding.bind(itemView)
                inBinding.msgInTextView.text = chat.msg
                inBinding.dateMsgInTextView.text = chat.date
            }
        }
    }

}