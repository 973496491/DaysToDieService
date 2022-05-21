package com.loko.utils.utils

import com.loko.utils.entity.common.Whitelist

fun <T> MutableList<*>.containsEx(element: T): Boolean {
    this.forEach {
        if (it as Whitelist  == element) {
            return true
        }
    }
    return false
}