package com.loko.utils.base

data class BaseResp(
    var code: Int,
    var message: String,
    var data: Any? = null,
)