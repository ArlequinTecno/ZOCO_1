package com.arlequins.zoco_1.model

import java.io.Serializable

data class NotificationModel(
    var id : String? = null,
    var uid: String? = null,
    var uname: String? = null,
    var uphone: String? = null,
    var article: Article? = null,
    var numArticle: String? = null,
    var total: String? = null,
    var date: String? = null,
    var state: String? = null,
    var type: String? = null,
    var msg: String? = null,
    var answer: String? = null,
    var urlPicture: String? = null
): Serializable
