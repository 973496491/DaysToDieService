package com.loko.utils.mapper

import com.loko.utils.config.TableConst
import com.loko.utils.entity.resp.*
import com.loko.utils.insert_entity.UploadIllustratedInfoEntity
import com.loko.utils.req.EditPropInfoReq
import com.loko.utils.req.EditZombieInfoReq
import org.apache.ibatis.annotations.*
import org.springframework.web.bind.annotation.RequestBody

/**
 * 图鉴信息
 */
@Mapper
interface IllustratedInfoMapper {

    /**
     * 查询所有的Menu
     */
    @Select("select id, `name` as menuName from ${TableConst.MENU}")
    fun findAllMenu(): MutableList<MenuInfo>?

    /**
     * 查询丧尸列表
     */
    @Select("SELECT " +
                "`id`, " +
                "`name`, " +
                "`type` " +
            "FROM " +
                "${TableConst.ZOMBIE_INFO} " +
            "WHERE " +
            "IF " +
                "(#{name} is NULL, 0=0, name LIKE CONCAT(CONCAT('%', #{name}), '%')) " +
            "AND IF " +
                "(#{type} is NULL, 0=0, type LIKE CONCAT(CONCAT('%', #{type}), '%')) " +
            "LIMIT #{startIndex}, #{pageSize}"
    )
    fun getZombieList(
        @Param("name") name: String?,
        @Param("type") type: String?,
        @Param("startIndex") startIndex: Int,
        @Param("pageSize") pageSize: Int,
    ): MutableList<ZombieNodeResp>?

    /**
     * 获取古神信息
     */
    @Select(
        "SELECT " +
            "`id`, " +
            "`name`, " +
            "`type`, " +
            "`image` AS `imageKey`, " +
            "`content` " +
        "FROM " +
            "${TableConst.ZOMBIE_INFO} " +
        "WHERE " +
            "`id` = #{id}"
    )
    fun getZombieInfo(id: Int): ZombieInfoResp?

    /**
     * 插入一条古神信息
     */
    @Insert(
        "INSERT INTO ${TableConst.ZOMBIE_INFO} ( " +
            "`type`, " +
            "`name`, " +
            "`image`, " +
            "`content` " +
        ") " +
        "VALUES " +
            "( " +
                "#{type}, " +
                "#{name}, " +
                "#{imageKey}, " +
                "#{content} " +
            ")"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertZombieInfo(info: EditZombieInfoReq)

    /**
     * 更新一条古神信息
     */
    @Update(
        "UPDATE ${TableConst.ZOMBIE_INFO} " +
                "set type = #{type}, name = #{name}, image = #{imageKey}, content = #{content} " +
                "where `id` = #{id}"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun updateZombieInfo(info: EditZombieInfoReq): Int

    /**
     * 查询古神 id
     */
    @Select("SELECT `id` FROM ${TableConst.ZOMBIE_INFO} WHERE `name` = #{name}")
    fun findZombieIdByName(name: String): Int?

    /**
     * 删除古神信息
     */
    @Delete("DELETE FROM ${TableConst.ZOMBIE_INFO} WHERE `id` = #{id}")
    fun deleteZombieInfo(id: Int): Int

    /**
     * 查询道具列表
     */
    @Select(
        "SELECT " +
            "`id`, " +
            "`name`, " +
            "`type` " +
        "FROM " +
            "${TableConst.PROP_INFO} " +
        "WHERE " +
        "IF " +
            "(#{name} is NULL, 0=0, name LIKE CONCAT(CONCAT('%', #{name}), '%')) " +
        "AND IF " +
            "(#{type} is NULL, 0=0, type LIKE CONCAT(CONCAT('%', #{type}), '%')) " +
        "LIMIT #{startIndex}, #{pageSize}"
    )
    fun getPropList(
        @Param("name") name: String?,
        @Param("type") type: String?,
        @Param("startIndex") startIndex: Int,
        @Param("pageSize") pageSize: Int,
    ): MutableList<PropNodeResp>?

    /**
     * 获取道具信息
     */
    @Select(
        "SELECT " +
            "`id`, " +
            "`name`, " +
            "`type`, " +
            "`image` AS `imageKey`, " +
            "`content` " +
        "FROM " +
            "${TableConst.PROP_INFO} " +
        "WHERE " +
            "`id` = #{id}"
    )
    fun getPropInfo(id: Int): PropInfoResp?

    /**
     * 插入一条古神信息
     */
    @Insert(
        "INSERT INTO ${TableConst.PROP_INFO} ( " +
            "`type`, " +
            "`name`, " +
            "`image`, " +
            "`content` " +
        ") " +
        "VALUES " +
            "( " +
                "#{type}, " +
                "#{name}, " +
                "#{imageKey}, " +
                "#{content} " +
            ")"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertPropInfo(info: EditPropInfoReq)

    /**
     * 更新一条道具信息
     */
    @Update(
        "UPDATE ${TableConst.PROP_INFO} " +
                "set type = #{type}, name = #{name}, image = #{imageKey}, content = #{content} " +
                "where `id` = #{id}"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun updatePropInfo(info: EditPropInfoReq): Int

    /**
     * 查询道具 id
     */
    @Select("SELECT `id` FROM ${TableConst.PROP_INFO} WHERE `name` = #{name}")
    fun findPropIdByName(name: String): Int?

    /**
     * 删除道具信息
     */
    @Delete("DELETE FROM ${TableConst.PROP_INFO} WHERE `id` = #{id}")
    fun deletePropInfo(id: Int): Int

    /**
     * 查询图鉴详情
     */
    @Select(
        "SELECT " +
            "`type`, " +
            "`image` AS imageKey, " +
            "`name`, " +
            "`content` " +
        "FROM " +
            "${TableConst.ILLUSTRATED_INFO} " +
        "WHERE parent_id = #{parentId} " +
        "AND IF " +
            "(#{name} is NULL, 0=0, name LIKE CONCAT(CONCAT('%', #{name}), '%')) " +
        "AND IF " +
            "(#{type} is NULL, 0=0, type LIKE CONCAT(CONCAT('%', #{type}), '%')) " +
        "LIMIT #{startIndex}, #{pageSize}"
    )
    fun findInfo(
        @Param("parentId") parentId: Int,
        @Param("name") name: String?,
        @Param("type") type: String?,
        @Param("startIndex") startIndex: Int,
        @Param("pageSize") pageSize: Int,
    ): MutableList<NodeInfo>?

    /**
     * 根据 name 查询所在 id
     */
    @Select("select id from ${TableConst.ILLUSTRATED_INFO} where `name` = #{name}")
    fun findIdByName(
        @Param("name") name: String,
    ): Int?

    /*
    * 插入一条信息
    */
    @Insert("insert into ${TableConst.ILLUSTRATED_INFO} (parent_id, type, name, image, content) values (#{parentId}, #{type}, #{name}, #{imageKey}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertInfo(
        @RequestBody req: UploadIllustratedInfoEntity,
    ): Int

    /**
     * 更新一条信息
     */
    @Update(
        "update ${TableConst.ILLUSTRATED_INFO} " +
        "set parent_id = #{parentId}, type = #{type}, name = #{name}, image = #{imageKey}, content = #{content} " +
        "where `id` = #{id}"
    )
    fun updateInfo(
        @RequestBody req: UploadIllustratedInfoEntity,
    ): Int

    /**
     * 删除一条信息
     */
    @Delete("delete from ${TableConst.ILLUSTRATED_INFO} where `id` = #{id}")
    fun deleteInfo(
        @Param("id") id: Int,
    ): Int
}