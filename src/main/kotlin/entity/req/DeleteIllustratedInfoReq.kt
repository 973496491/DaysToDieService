package com.loko.utils.req

import java.beans.ConstructorProperties

data class DeleteIllustratedInfoReq @ConstructorProperties(
    "name"
) constructor(
    var name: String = "",
)
