package com.loko.utils.req

import java.beans.ConstructorProperties

data class SubtractDownloadCountReq @ConstructorProperties(
    "token",
) constructor(
    val token: String,
)
