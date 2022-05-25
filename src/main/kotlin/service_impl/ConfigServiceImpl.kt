package com.loko.utils.service_impl

import com.loko.utils.config.CacheModel
import com.loko.utils.entity.common.Whitelist
import com.loko.utils.entity.resp.ModDataResp
import com.loko.utils.mapper.ConfigMapper
import com.loko.utils.service.ConfigService
import com.loko.utils.utils.RedisUtils
import com.loko.utils.utils.containsEx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Suppress("unused")
@Service
class ConfigServiceImpl : ConfigService {

    @Autowired
    private lateinit var configMapper: ConfigMapper

    @Resource
    private lateinit var redisUtil: RedisUtils
    override fun getWhitelistItemId(modName: String, modAuthor: String): Int {
        return configMapper.getHasWhitelistItem(modName, modAuthor) ?: -1
    }

    @Suppress("UNCHECKED_CAST")
    override fun getAllWhitelist(): MutableList<Whitelist>? {
        val hKey = CacheModel.Config.KEY_WHITELIST
        val whitelist = redisUtil.hget(hKey, CacheModel.EMPTY_ID)
        return if (whitelist is MutableList<*>) {
            whitelist as MutableList<Whitelist>
        } else {
            configMapper.getAllWhitelist()?.let {
                redisUtil.hset(hKey, CacheModel.EMPTY_ID, it)
                it
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun insertWhitelistItem(item: Whitelist): Boolean {
        val hKey = CacheModel.Config.KEY_WHITELIST
        val whitelist = redisUtil.hget(hKey, CacheModel.EMPTY_ID)

        val insert = { list: MutableList<Whitelist> ->
            configMapper.insertWhitelistItem(item)
            if (item.id  > 0) {
                redisUtil.hset(hKey, CacheModel.EMPTY_ID, list.add(item))
                true
            } else {
                false
            }
        }

        return if (whitelist is MutableList<*>) {
            if (whitelist.containsEx(item)) {
                true
            } else {
                insert.invoke(whitelist as MutableList<Whitelist>)
            }
        } else {
            insert.invoke(mutableListOf())
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun updateWhitelistItem(item: Whitelist): Boolean {
        val hKey = CacheModel.Config.KEY_WHITELIST
        val whitelist = redisUtil.hget(hKey, CacheModel.EMPTY_ID)

        val update = { list: MutableList<Whitelist> ->
            val isSuc = configMapper.updateWhitelistItem(item) > 0
            if (isSuc) {
                val newList = list.map {
                    if (it.id == item.id) {
                        item
                    } else {
                        it
                    }
                }
                redisUtil.hset(hKey, CacheModel.EMPTY_ID, newList)
                true
            } else {
                false
            }
        }
        return update.invoke(whitelist as MutableList<Whitelist>)
    }

    override fun getXmlContentFromSteamId(id: String, modKey: String): MutableList<ModDataResp>? {
        return configMapper.getXmlContentFromSteamId(id, modKey)
    }
}