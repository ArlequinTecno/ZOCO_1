package com.arlequins.zoco_1.ui.details.detailsNotification

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
import com.arlequins.zoco_1.ui.details.detailsNotification.adapter.ChatAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailNotificationFragment : Fragment() {

    private lateinit var detailsNotificationBinding: FragmentDetailNotificationBinding
    private lateinit var detailsNotificationViewModel: DetailNotificationViewModel
    private lateinit var chatAdapter: ChatAdapter
    private var chatList: ArrayList<Chat> = ArrayList<Chat>()
    private val args: DetailNotificationFragmentArgs by navArgs()



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        detailsNotificationBinding = FragmentDetailNotificationBinding.inflate(inflater, container, false)
        detailsNotificationViewModel = ViewModelProvider(this)[DetailNotificationViewModel::class.java]

        val notification = args.notificationModel
        with(detailsNotificationViewModel) {
            loadMsg(notification.id)
            showMsg.observe(viewLifecycleOwner){ msg->
                showMsg(msg)
            }
        }

        with(detailsNotificationBinding){
            nameDetailProductTextView.text = notification.article?.name
            priceDetailProductTextView.text = notification.article?.price
            amountDetailProductTextView.text = notification.numArticle
            totalDetailProductTextView.text = notification.total
            dateDetailProductTextView.text = notification.date

            sendMsgButton.setOnClickListener {
                detailsNotificationViewModel.sendMsg(
                    notification,
                    msgInputText.text.toString(),
                    getDate()
                )
                msgInputText.setText("")

            }
        }
        initMsgRecyclerView()
        return detailsNotificationBinding.root
    }


    private fun initMsgRecyclerView() {
        with(detailsNotificationBinding){
            msgRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@DetailNotificationFragment.requireContext())
                adapter = chatAdapter
                setHasFixedSize(false)
            }
        }
    }


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