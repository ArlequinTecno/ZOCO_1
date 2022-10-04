package com.arlequins.zoco_1.ui.details.detailsArticle

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arlequins.zoco_1.databinding.FragmentDetailsArticleBinding

class DetailsArticleFragment : Fragment() {

    private lateinit var detailsArticleBinding: FragmentDetailsArticleBinding
    private lateinit var detailsArticleViewModel: DetailsArticleViewModel
    private val args : DetailsArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsArticleViewModel = ViewModelProvider(this)[DetailsArticleViewModel::class.java]
        detailsArticleBinding = FragmentDetailsArticleBinding.inflate(inflater, container, false)

        val article = args.article

        with(detailsArticleBinding){
            nameArticleDetailsTextView.text = article.name?.uppercase()
            priceArticleDetailsTextView.text = article.price
            storeArticleDetailsTextView.text = article.store
            categoryArticleDetailsTextView.text = article.category
            nameSellerTextView.text = article.uname
            phonSellerTextView.text = article.phone
            descriptionArticleDetailsTextView.text = article.description

            //if (!article.urlPicture.isNullOrEmpty())
                //Picasso.get().load(article.urlPicture).into(pictureDetailsImageView)

            requestArticleDetailsButton.setOnClickListener {
                findNavController().navigate(DetailsArticleFragmentDirections.actionNavDetailsArticleFragmentToNavTrolleyFragment(article))
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(DetailsArticleFragmentDirections.actionNavDetailsArticleFragmentToNavIndex())
        }
        return detailsArticleBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}