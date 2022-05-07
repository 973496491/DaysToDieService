package com.loko.utils.service

import com.loko.utils.resp.KeyInfoResp
import com.loko.utils.resp.Whitelist
import com.loko.utils.resp.XmlContentResp
import org.springframework.stereotype.Service

@Service
interface ConfigService {

    fun getAllWhitelist(): MutableList<Whitelist>?

    fun getXmlDataForKey(
        key:  Int,
        modName: String,
        modAuthor: String,
    ): MutableList<XmlContentResp>?

    fun getKeyForId(keyId: String): KeyInfoResp?
}