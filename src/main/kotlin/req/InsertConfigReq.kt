package com.loko.utils.req

import java.beans.ConstructorProperties

data class InsertConfigReq @ConstructorProperties(
    "id", "userId", "gamePath", "token"
) constructor(
    var id: Int = -1,
    var userId: Int = -1,
    val gamePath: String,
    val token: String,
)