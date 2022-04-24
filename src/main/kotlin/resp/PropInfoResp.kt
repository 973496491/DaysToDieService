package com.loko.utils.resp

import java.beans.ConstructorProperties

data class PropInfoResp @ConstructorProperties(
    "id", "type", "name", "imageKey", "content"
) constructor(
    val id: Int,
    val type: String?,
    val name: String?,
    val imageKey: String?,
    val content: String?,
)