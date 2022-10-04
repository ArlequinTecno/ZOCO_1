package com.arlequins.zoco_1.model

import java.io.Serializable

data class Article(
    var id : String? = null,
    var uid: String? = null,
    var uname: String? = null,
    var phone: String? = null,
    var name : String? = null,
    var price : String? = null,
    var description : String? = null,
    var category : String? = null,
    var store : String? = null,
    var state : String? = null,
    var urlPicture : String? = null
    ): Serializable
