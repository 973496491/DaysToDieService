package com.loko.utils.req

import java.beans.ConstructorProperties

data class XmlContentReq @ConstructorProperties(
    "key", "modName", "modAuthor"
) constructor(
    val key: String,
    val modName: String,
    val modAuthor: String,
)