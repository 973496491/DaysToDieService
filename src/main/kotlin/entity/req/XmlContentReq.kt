package com.loko.utils.entity.req

import java.beans.ConstructorProperties

data class XmlContentReq @ConstructorProperties(
    "steamId", "modKey"
) constructor(
    val steamId: String,
    val modKey: String,
)