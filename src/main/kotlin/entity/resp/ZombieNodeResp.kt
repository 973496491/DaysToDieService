package com.loko.utils.entity.resp

import java.beans.ConstructorProperties

data class ZombieNodeResp @ConstructorProperties(
    "id", "type", "name"
) constructor(
    val id: Int,
    val type: String?,
    val name: String?,
)