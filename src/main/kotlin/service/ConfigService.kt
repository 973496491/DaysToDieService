package com.loko.utils.service

import com.loko.utils.entity.common.Whitelist
import com.loko.utils.entity.resp.ModDataResp
import org.springframework.stereotype.Service

@Service
interface ConfigService {

    fun getWhitelistItemId(modName: String, modAuthor: String): Int

    fun getAllWhitelist(): MutableList<Whitelist>?

    fun insertWhitelistItem(item: Whitelist): Boolean

    fun updateWhitelistItem(item: Whitelist): Boolean

    fun getXmlContentFromSteamId(id: String, modKey: String): MutableList<ModDataResp>?
}