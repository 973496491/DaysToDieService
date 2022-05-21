package com.loko.utils.service

import com.loko.utils.entity.common.Whitelist
import com.loko.utils.entity.resp.KeyInfoResp
import com.loko.utils.entity.resp.XmlContentResp
import org.springframework.stereotype.Service

@Service
interface ConfigService {

    fun getWhitelistItemId(modName: String, modAuthor: String): Int

    fun getAllWhitelist(): MutableList<Whitelist>?

    fun insertWhitelistItem(item: Whitelist): Boolean

    fun updateWhitelistItem(item: Whitelist): Boolean

    fun getXmlDataForKey(
        key:  Int,
        modName: String,
        modAuthor: String,
    ): MutableList<XmlContentResp>?

    fun getKeyForId(keyId: String): KeyInfoResp?
}