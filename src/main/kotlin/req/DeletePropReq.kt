package com.loko.utils.req

import java.beans.ConstructorProperties

data class DeletePropReq @ConstructorProperties(
    "id"
) constructor(
    val id: Int,
)