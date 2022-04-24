package com.loko.utils.insert_entity

import java.beans.ConstructorProperties

data class DllEntity @ConstructorProperties(
    "id", "key", "version", "size"
) constructor(
    var id: Int = -1,
    val key: String,
    val version: String,
    var size: Long,
    var name: String,
)