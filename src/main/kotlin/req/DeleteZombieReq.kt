package com.loko.utils.req

import java.beans.ConstructorProperties

data class DeleteZombieReq @ConstructorProperties(
    "id"
) constructor(
    val id: Int,
)