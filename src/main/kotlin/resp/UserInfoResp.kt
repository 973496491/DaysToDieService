package com.loko.utils.resp

data class UserInfoResp(
    val userName: String?,
    val gamePath: String?,
    val fastDownloadCount: Int = 0,
    val admin: Boolean = false,
)
