package com.loko.utils.entity.resp

import java.beans.ConstructorProperties

data class ModDataResp @ConstructorProperties(
    "publicKey", "lv", "versionCode", "content", "contentKey"
) constructor(
    val publicKey: String?,
    val lv: String?,
    val versionCode: String?,
    val content: String?,
    val contentKey: String?,
)