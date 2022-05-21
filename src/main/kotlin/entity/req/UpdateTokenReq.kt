package com.loko.utils.req

import java.beans.ConstructorProperties

data class UpdateTokenReq @ConstructorProperties(
    "id", "userId", "token"
) constructor(
    var id: Int = -1,
    val userId: Int,
    val token: String,
)
