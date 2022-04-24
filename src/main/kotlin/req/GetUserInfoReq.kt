package com.loko.utils.req

import java.beans.ConstructorProperties

data class GetUserInfoReq @ConstructorProperties(
    "token",
) constructor(
    val token: String,
)
