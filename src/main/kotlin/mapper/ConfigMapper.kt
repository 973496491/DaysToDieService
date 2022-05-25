package com.loko.utils.mapper

import com.loko.utils.config.TableConst
import com.loko.utils.entity.common.Whitelist
import com.loko.utils.entity.resp.ModDataResp
import org.apache.ibatis.annotations.*

@Mapper
interface ConfigMapper {

    /**
     * 获取白名单id
     */
    @Select("SELECT `id` FROM whitelist WHERE mod_name = #{modName} AND mod_author = #{modAuthor}")
    fun getHasWhitelistItem(modName: String, modAuthor: String): Int?

    /**
     * 获取全部白名单信息
     */
    @Select("SELECT `id`, `mod_name` as `name`, mod_author as author, mod_desc as `desc`, `is_online` as online, mod_key as modKey FROM `${TableConst.WHITELIST}`")
    fun getAllWhitelist(): MutableList<Whitelist>?

    /**
     * 插入一条白名单数据
     */
    @Insert("INSERT INTO ${TableConst.WHITELIST} (mod_name, mod_author, mod_desc, is_online, mod_key) VALUES (#{name}, #{author}, #{desc}, #{online}, #{modKey})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertWhitelistItem(item: Whitelist)

    @Update("UPDATE ${TableConst.WHITELIST} SET mod_name = #{name}, mod_author = #{author}, mod_desc = #{desc}, is_online = #{online}, mod_key = #{modKey} WHERE id = #{id}")
    fun updateWhitelistItem(item: Whitelist): Int

    /**
     * 根据SteamId查询最新的密钥
     */
    @Select("SELECT " +
                "a.version_code as versionCode, " +
                "b.lv, " +
                "b.public_key as publicKey, " +
                "c.content_key as contentKey, " +
                "c.content  " +
            "FROM  " +
                "`${TableConst.STEAM_ID}` AS a  " +
            "INNER JOIN `${TableConst.KEYS}` AS b ON a.version_code = b.version_code   " +
            "INNER JOIN `${TableConst.XML_CONTENT}` as c ON a.version_code = c.version_code  AND c.mod_key = #{modKey}" +
            "WHERE  " +
                "a.steam_id = #{id}"
    )
    fun getXmlContentFromSteamId(id: String, modKey: String): MutableList<ModDataResp>?
}