package com.loko.utils.entity.common

import java.beans.ConstructorProperties

data class Whitelist @ConstructorProperties(
    "id", "name", "author", "desc", "online", "modKey"
) constructor(
    var id: Int = -1,
    val name: String,
    val author: String,
    val desc: String,
    val online: Boolean,
    val modKey: String,
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Whitelist) {
            this.online == other.online && this.author == other.author && this.desc == other.desc && this.name == other.name && this.modKey == other.modKey
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + online.hashCode()
        result = 31 * result + modKey.hashCode()
        return result
    }
}