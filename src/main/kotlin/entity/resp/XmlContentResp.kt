package com.loko.utils.entity.resp

import java.beans.ConstructorProperties

data class XmlContentResp @ConstructorProperties(
    "id", "key", "content"
) constructor(
    val id: Int,
    val key: String?,
    val content: String?,
)

data class XmlInfoResp @ConstructorProperties(
    "publicKey", "iv", "xmlList"
) constructor(
    val publicKey: String?,
    val iv: String?,
    val xmlList: MutableList<XmlContentResp>
)