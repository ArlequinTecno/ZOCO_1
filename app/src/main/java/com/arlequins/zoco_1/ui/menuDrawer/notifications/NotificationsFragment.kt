package com.arlequins.zoco_1.ui.menuDrawer.notifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlequins.zoco_1.databinding.FragmentNotificationsBinding
import com.arlequins.zoco_1.model.NotificationModel

class NotificationsFragment : Fragment() {
    private lateinit var notificationsBinding: FragmentNotificationsBinding
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var notificationAdapter: NotificationAdapter
    private var notificationList: ArrayList<NotificationModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsBinding = FragmentNotificationsBinding.inflate(inflater, container, false)
        notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]

        with(notificationsViewModel){
            loadNotifications()
            showMsg.observe(viewLifecycleOwner){ msg->
                showMsg(msg)
            }
            notificationsList.observe(viewLifecycleOwner){ notificationListLoad ->
                notificationAdapter.appendItems(notificationListLoad)
            }
        }

        notificationAdapter = NotificationAdapter(notificationList, onItemClicked = {onNotificationClicked(it)})

        notificationsBinding.notificationsRecycleView.apply {
            layoutManager = LinearLayoutManager(this@NotificationsFragment.requireContext())
            adapter = notificationAdapter
            setHasFixedSize(false)
        }

        return notificationsBinding.root
    }

    private fun onNotificationClicked(it: NotificationModel) {
        findNavController().navigate(NotificationsFragmentDirections.actionNavNotificationsToNavDetailNotificationFragment(it))
    }
    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}