package com.loko.utils.req

import java.beans.ConstructorProperties

data class GetZombieInfoReq @ConstructorProperties(
    "id"
) constructor(
    val id: Int,
)