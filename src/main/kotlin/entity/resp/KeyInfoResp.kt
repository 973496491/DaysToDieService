package com.loko.utils.entity.resp

import java.beans.ConstructorProperties

data class KeyInfoResp @ConstructorProperties(
    "id", "key", "iv"
) constructor(
    val id: Int,
    val key: String?,
    val iv: String?,
)