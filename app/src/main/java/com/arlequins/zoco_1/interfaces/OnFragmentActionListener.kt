package com.arlequins.zoco_1.interfaces

import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.model.NotificationModel
import com.arlequins.zoco_1.model.Store

interface OnFragmentActionListener {
    fun onClickedFragmentCardView(article: Article)
    fun navTrolleyToIndex()
    fun onClickedMyArticle(it: Article)
    fun onClickedStore(it: Store)
}