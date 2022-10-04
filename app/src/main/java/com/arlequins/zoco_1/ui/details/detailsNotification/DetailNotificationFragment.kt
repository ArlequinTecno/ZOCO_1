package com.arlequins.zoco_1.ui.details.detailsNotificationIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.arlequins.zoco_1.databinding.FragmentDetailNotificationBinding

class DetailNotificationFragment : Fragment() {

    private lateinit var detailsNotificationBinding: FragmentDetailNotificationBinding
    private lateinit var detailsNotificationViewModel: DetailNotificationViewModel
    private val args: DetailNotificationFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        detailsNotificationBinding = FragmentDetailNotificationBinding.inflate(inflater, container, false)
        detailsNotificationViewModel = ViewModelProvider(this)[DetailNotificationViewModel::class.java]

        val notification = args.notificationModel

        with(detailsNotificationBinding){
            nameDetailProductTextView.text = notification.article?.name
            priceDetailProductTextView.text = notification.article?.price
            amountDetailProductTextView.text = notification.numArticle
            totalDetailProductTextView.text = notification.total
            dateDetailProductTextView.text = notification.date
        }


        return detailsNotificationBinding.root
    }



}