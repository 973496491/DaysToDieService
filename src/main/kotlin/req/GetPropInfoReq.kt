package com.loko.utils.req

import java.beans.ConstructorProperties

data class GetPropInfoReq @ConstructorProperties(
    "id"
) constructor(
    val id: Int,
)