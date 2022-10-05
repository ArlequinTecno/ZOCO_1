package com.arlequins.zoco_1.model

import java.io.Serializable

data class Store(
    var id : String? = null,
    var uid : String? = null,
    var name : String? = null,
    var uname : String? = null,
    var description : String? = null,
    var location : String? = null,
    var state : String? = null
): Serializable
