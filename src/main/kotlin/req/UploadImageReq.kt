package com.loko.utils.req

import java.beans.ConstructorProperties

data class UploadImageReq @ConstructorProperties(
    "fileName", "fileBase64"
) constructor(
    val fileName: String,
    val fileBase64: String,
)
