package com.loko.utils.entity.resp

import java.beans.ConstructorProperties

data class IllustratedInfoResp @ConstructorProperties(
    "list"
) constructor(
    var list: MutableList<IllustratedInfo>?,
)

data class IllustratedInfo @ConstructorProperties(
    "id", "menuName", "list",
) constructor(
    val id: Int,
    val menuName: String,
    var itemNode: List<NodeInfo>? = null,
)

data class NodeInfo @ConstructorProperties(
    "type", "name", "imageUrl", "content"
) constructor(
    val type: String?,
    val name: String?,
    val imageKey: String?,
    val content: String?,
)

data class MenuInfo(
    val id: Int,
    val menuName: String,
)