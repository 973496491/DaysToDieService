package com.loko.utils.mapper

import com.loko.utils.config.TableConst
import com.loko.utils.entity.common.Whitelist
import com.loko.utils.entity.resp.KeyInfoResp
import com.loko.utils.entity.resp.XmlContentResp
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
    @Select("SELECT `id`, `mod_name` as `name`, mod_author as author, mod_desc as `desc`, `is_online` as online FROM `${TableConst.WHITELIST}`")
    fun getAllWhitelist(): MutableList<Whitelist>?

    /**
     * 插入一条白名单数据
     */
    @Insert("INSERT INTO ${TableConst.WHITELIST} (mod_name, mod_author, mod_desc, is_online) VALUES (#{name}, #{author}, #{desc}, #{online})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertWhitelistItem(item: Whitelist)

    @Update("UPDATE ${TableConst.WHITELIST} SET mod_name = #{name}, mod_author = #{author}, mod_desc = #{desc}, is_online = #{online} WHERE id = #{id}")
    fun updateWhitelistItem(item: Whitelist): Int

    /**
     * 根据Key查询Xml内容
     */
    @Select("SELECT " +
                "`id`, `key`, `content` " +
            "FROM " +
                "${TableConst.XML_CONTENT} " +
            "WHERE " +
                "`key_id` = #{keyId} " +
            "AND " +
                "mod_name = #{modName} " +
            "AND " +
                "mod_author = #{modAuthor}"
    )
    fun getXmlDataForKey(keyId: Int, modName: String, modAuthor: String): MutableList<XmlContentResp>?

    /**
     * 根据密钥id值和 steam id 查询密钥
     */
    @Select("SELECT `id`, `key`, iv FROM `${TableConst.KEYS}` WHERE `key_id` = #{keyId}")
    fun getKeyForId(keyId: String): KeyInfoResp?
}