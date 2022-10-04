package com.arlequins.zoco_1.ui.trolley

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.arlequins.zoco_1.databinding.FragmentTrolleyBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.ui.details.detailsArticle.DetailsArticleFragmentArgs
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TrolleyFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var trolleyBinding: FragmentTrolleyBinding
    private lateinit var trolleyViewModel: TrolleyViewModel
    private val args : DetailsArticleFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        trolleyBinding = FragmentTrolleyBinding.inflate(inflater, container, false)
        trolleyViewModel = ViewModelProvider(this)[TrolleyViewModel::class.java]

        val article = args.article

        with(trolleyViewModel){
            showMsg.observe(viewLifecycleOwner){ msg ->
                showMsg(msg)
            }
            createNotification.observe(viewLifecycleOwner){
                listenerInterface?.navTrolleyToIndex()
            }
        }
        with(trolleyBinding){
            nameArticleRequestTextView.text = article.name?.uppercase()
            priceArticleRequestTextView.text = article.price
            numberArticleInputText.setText("1")
            upDateTotal(article)
            numberArticleInputText.setOnClickListener{
                upDateTotal(article)
            }
            numberArticleInputText.setOnClickListener {
                upDateTotal(article)
            }
            requestButton.setOnClickListener{
                upDateTotal(article)
                trolleyViewModel.validateFields(article,
                    numberArticleInputText.text.toString(),
                    msgInputText.text.toString(),
                    totalArticleTextView.text.toString(),
                    getDate()
                )
            }
        }

        return trolleyBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        if (context is OnFragmentActionListener){
            listenerInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerInterface = null
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    private fun upDateTotal(article: Article){
        with(trolleyViewModel){
            calculateTotal(trolleyBinding.numberArticleInputText.text.toString(), article.price)
            total.observe(viewLifecycleOwner){total->
                if (total != null) {
                    trolleyBinding.totalArticleTextView.text = total
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate(): String = LocalDateTime.now()
        .format(DateTimeFormatter
            .ofPattern("MMM dd yyyy, hh:mm:ss a"))

}