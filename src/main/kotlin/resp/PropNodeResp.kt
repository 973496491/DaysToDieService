package com.loko.utils.resp

import java.beans.ConstructorProperties

data class PropNodeResp @ConstructorProperties(
    "id", "type", "name"
) constructor(
    val id: Int,
    val type: String?,
    val name: String?,
)