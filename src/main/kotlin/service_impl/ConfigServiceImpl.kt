package com.loko.utils.service_impl

import com.loko.utils.config.CacheModel
import com.loko.utils.mapper.ConfigMapper
import com.loko.utils.resp.KeyInfoResp
import com.loko.utils.resp.Whitelist
import com.loko.utils.resp.XmlContentResp
import com.loko.utils.service.ConfigService
import com.loko.utils.utils.RedisUtils
import com.loko.utils.utils.safeToStringOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class ConfigServiceImpl : ConfigService {

    @Autowired
    private lateinit var configMapper: ConfigMapper

    @Resource
    private lateinit var redisUtil: RedisUtils

    @Suppress("UNCHECKED_CAST")
    override fun getAllWhitelist(): MutableList<Whitelist>? {
        val hKey = CacheModel.Config.KEY_WHITELIST
        val whitelist = redisUtil.hget(hKey, CacheModel.EMPTY_ID) as? MutableList<Whitelist>
        return whitelist ?: configMapper.getAllWhitelist()?.let {
            redisUtil.hset(hKey, CacheModel.EMPTY_ID, it)
            it
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getXmlDataForKey(key: Int, modName: String, modAuthor: String): MutableList<XmlContentResp>? {
        val hKey = CacheModel.Config.KEY_XML_CONTENT
        val xmlList = redisUtil.hget(hKey, "$key$modName$modAuthor") as? MutableList<XmlContentResp>
        return xmlList ?: configMapper.getXmlDataForKey(key, modName, modAuthor)?.let {
            redisUtil.hset(hKey, "$key", it)
            it
        }
    }

    override fun getKeyForId(keyId: String): KeyInfoResp? {
        return configMapper.getKeyForId(keyId)
    }
}