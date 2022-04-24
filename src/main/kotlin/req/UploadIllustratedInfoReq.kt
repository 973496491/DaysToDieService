package com.loko.utils.req

import java.beans.ConstructorProperties

data class UploadIllustratedInfoReq @ConstructorProperties(
    "parentId", "type", "name", "imageKey", "content"
) constructor(
    val parentId: Int = 0,
    val type: String = "",
    val name: String = "",
    val imageKey: String = "",
    val content: String = "",
)
