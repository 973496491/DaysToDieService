package com.loko.utils.mapper

import com.loko.utils.cons.TableConst
import com.loko.utils.req.RegisterReq
import com.loko.utils.resp.KeyInfoResp
import com.loko.utils.resp.Whitelist
import com.loko.utils.resp.XmlContentResp
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.springframework.web.bind.annotation.RequestBody

@Mapper
interface ConfigMapper {

    /**
     * 获取全部白名单信息
     */
    @Select("SELECT `mod_name` as `name`, mod_author as author, mod_desc as `desc`, `is_online` as online FROM `whitelist`")
    fun getAllWhitelist(): MutableList<Whitelist>?

    /**
     * 查询白名单信息是否存在
     */

    /**
     * 插入新的白名单
     */
    @Insert("insert into ${TableConst.USER} (`username`, `password`) values (#{userName}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertNewUser(
        @RequestBody user: RegisterReq,
    )

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
    @Select("SELECT `id`, `key`, iv FROM `keys` WHERE `key_id` = #{keyId}")
    fun getKeyForId(keyId: String): KeyInfoResp?
}