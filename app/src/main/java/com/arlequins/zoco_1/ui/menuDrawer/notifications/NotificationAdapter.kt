package com.arlequins.zoco_1.ui.menuDrawer.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.CardViewNotificationItemBinding
import com.arlequins.zoco_1.model.NotificationModel


class NotificationAdapter(
    private val notificationList: ArrayList<NotificationModel>,
    private val onItemClicked: (NotificationModel) -> Unit
    ) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_notification_item,parent,false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notificationList[position]
        holder.bind(notification)
        holder.itemView.setOnClickListener {onItemClicked(notificationList[position])}
    }

    override fun getItemCount(): Int = notificationList.size

    fun appendItems(newList: ArrayList<NotificationModel>){
        notificationList.clear()
        notificationList.addAll(newList)
        notifyDataSetChanged()
    }
    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = CardViewNotificationItemBinding.bind(itemView)

        fun bind(notification: NotificationModel){
            with(binding){
                if (notification.type == "client") {
                    nameContactTextView.text = notification.article?.uname
                } else {
                    nameContactTextView.text = notification.uname
                }

                dateNotificationTextView.text = notification.date
                nameProductNotificationTextView.text = notification.article?.name

                when (notification.state) {
                    "received" -> stateImageView.setImageResource(R.drawable.ic_received_msg)
                    "viewed" -> stateImageView.setImageResource(R.drawable.ic_check_mark)
                    "sent" -> stateImageView.setImageResource(R.drawable.ic_send_msg)
                    else -> stateImageView.setImageResource(R.drawable.ic_x_mark)
                }

                /*if (notification.article?.urlPicture.isNullOrEmpty()){
                    Picasso.get().load(notification.urlPicture).into(contactImageView)
                }*/
            }

        }
    }

}

