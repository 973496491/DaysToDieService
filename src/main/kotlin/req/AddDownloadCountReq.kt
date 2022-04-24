package com.loko.utils.req

import java.beans.ConstructorProperties

data class AddDownloadCountReq @ConstructorProperties(
    "id", "userName", "count",
) constructor(
    var id: Int = -1,
    var token: String,
    val count: Int,
)