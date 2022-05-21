package com.loko.utils.req

import java.beans.ConstructorProperties

data class EditPropInfoReq @ConstructorProperties(
    "id", "name", "type", "imageKey", "content"
) constructor(
    var id: Int?,
    val name: String,
    val type: String,
    val imageKey: String,
    val content: String,
)
