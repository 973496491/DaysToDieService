package com.loko.utils.req

import java.beans.ConstructorProperties

data class GetIllustratedListReq @ConstructorProperties(
    "type", "name", "pageIndex", "pageSize"
) constructor(
    val type: String?,
    val name: String?,
    val pageIndex: Int = 0,
    val pageSize: Int = 20,
)