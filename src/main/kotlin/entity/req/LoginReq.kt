package com.loko.utils.req

import java.beans.ConstructorProperties

data class LoginReq @ConstructorProperties(
    "userName", "password"
) constructor(
    val userName: String,
    val password: String,
)