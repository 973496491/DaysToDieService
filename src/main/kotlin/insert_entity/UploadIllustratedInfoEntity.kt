package com.loko.utils.insert_entity

import java.beans.ConstructorProperties

data class UploadIllustratedInfoEntity @ConstructorProperties(
    "id", "parentId", "type", "name", "imageKey", "content"
) constructor(
    var id: Int = -1,
    var parentId: Int,
    var type: String = "",
    var name: String = "",
    var imageKey: String = "",
    var content: String = "",
)
