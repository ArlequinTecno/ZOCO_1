package com.arlequins.zoco_1.ui.details.detailsNotification

import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlequins.zoco_1.databinding.FragmentDetailNotificationBinding
import com.arlequins.zoco_1.model.Chat
import com.arlequins.zoco_1.model.NotificationModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailNotificationFragment : Fragment() {

    private lateinit var detailsNotificationBinding: FragmentDetailNotificationBinding
    private lateinit var detailsNotificationViewModel: DetailNotificationViewModel
    private lateinit var chatAdapter: DetailNotificationAdapter
    private var chatList: ArrayList<Chat> = ArrayList()
    private val args: DetailNotificationFragmentArgs by navArgs()
    private lateinit var notification: NotificationModel
    private lateinit var llmanager: LinearLayoutManager




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        detailsNotificationBinding = FragmentDetailNotificationBinding.inflate(inflater, container, false)
        detailsNotificationViewModel = ViewModelProvider(this)[DetailNotificationViewModel::class.java]

        notification = args.notificationModel
        with(detailsNotificationViewModel) {
            loadMsg(notification.id)
            showMsg.observe(viewLifecycleOwner){ msg->
                showMsg(msg)
            }
            chatListM.observe(viewLifecycleOwner){ chatList ->
                chatAdapter.appendItems(chatList)

            }
            sentMsg.observe(viewLifecycleOwner){ chatItem ->
                if (chatItem != null) {
                    chatAdapter.appendItem(chatItem)
                    llmanager.scrollToPositionWithOffset(chatList.size-1, 10)
                }
            }

        }
        chatAdapter = DetailNotificationAdapter(
            chatList,
            onItemClicked = { onMsgClicked(it) }
        )

        with(detailsNotificationBinding){
            msgRecyclerView.apply {
                llmanager =  LinearLayoutManager(this@DetailNotificationFragment.requireContext())
                layoutManager = llmanager
                adapter = chatAdapter
                setHasFixedSize(false)
            }

            nameDetailProductTextView.text = notification.article?.name
            priceDetailProductTextView.text = notification.article?.price
            amountDetailProductTextView.text = notification.numArticle
            totalDetailProductTextView.text = notification.total
            dateDetailProductTextView.text = notification.date

            sendMsgButton.setOnClickListener {
                with(detailsNotificationBinding) {
                    detailsNotificationViewModel.sendMsg(
                        notification,
                        msgInputText.text.toString(),
                        getDate(),
                    )
                    msgInputText.setText("")

                }
            }
        }
        return detailsNotificationBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)



    private fun onMsgClicked(it: Chat) {
        Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate(): String = LocalDateTime.now()
        .format(DateTimeFormatter
            .ofPattern("MMM dd yyyy, hh:mm:ss a"))


}