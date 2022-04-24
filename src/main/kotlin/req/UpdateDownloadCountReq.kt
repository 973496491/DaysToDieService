package com.loko.utils.req

import java.beans.ConstructorProperties

data class UpdateDownloadCountReq @ConstructorProperties(
    "token", "count",
) constructor(
    val token: String,
    val count: Int,
)
