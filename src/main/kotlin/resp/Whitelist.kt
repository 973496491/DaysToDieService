package com.loko.utils.resp

import java.beans.ConstructorProperties

data class Whitelist @ConstructorProperties(
    "name", "author", "desc", "isOnline"
) constructor(
    val name: String,
    val author: String,
    val desc: String,
    val online: Boolean,
)
