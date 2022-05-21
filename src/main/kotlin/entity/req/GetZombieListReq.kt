package com.loko.utils.req

import java.beans.ConstructorProperties

data class GetZombieListReq @ConstructorProperties(
    "type", "name", "pageIndex", "pageSize"
) constructor(
    val type: String?,
    val name: String?,
    val pageIndex: Int?,
    val pageSize: Int = 20,
)