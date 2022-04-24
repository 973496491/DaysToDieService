package com.loko.utils.req

import java.beans.ConstructorProperties

data class RegisterReq @ConstructorProperties(
    "id", "userName", "password"
) constructor(
    var id: Int = -1,
    val userName: String,
    val password: String,
)