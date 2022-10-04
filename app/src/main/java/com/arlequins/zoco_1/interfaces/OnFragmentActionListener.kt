package com.arlequins.zoco_1.interfaces

import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.model.NotificationModel

interface OnFragmentActionListener {
    fun onClickedFragmentCardView(article: Article)
    fun navTrolleyToIndex()
}