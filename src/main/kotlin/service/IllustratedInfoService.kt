package com.loko.utils.service

import com.loko.utils.insert_entity.UploadIllustratedInfoEntity
import com.loko.utils.req.EditPropInfoReq
import com.loko.utils.req.EditZombieInfoReq
import com.loko.utils.resp.*
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Service

@Service
interface IllustratedInfoService {

    /**
     * 查询所有的Menu
     */
    fun findAllMenu(): MutableList<MenuInfo>?

    /**
     * 查询古神列表
     */
    fun getZombieList(
        name: String?,
        type: String?,
        startIndex: Int,
        pageSize: Int,
    ): MutableList<ZombieNodeResp>?

    /**
     * 查询古神信息
     */
    fun getZombieInfo(id: Int): ZombieInfoResp?

    /**
     * 插入一条古神信息
     */
    fun insertZombieInfo(info: EditZombieInfoReq)

    /**
     * 更新一条古神信息
     */
    fun updateZombieInfo(info: EditZombieInfoReq): Boolean

    /**
     * 根据name查询所古神在id
     */
    fun findZombieIdByName(name: String): Int

    /**
     * 删除古神信息
     */
    fun deleteZombieInfo(id: Int): Boolean

    /**
     * 查询道具列表
     */
    fun getPropList(
        name: String?,
        type: String?,
        startIndex: Int,
        pageSize: Int,
    ): MutableList<PropNodeResp>?

    /**
     * 查询道具信息
     */
    fun getPropInfo(id: Int): PropInfoResp?

    /**
     * 插入一条道具信息
     */
    fun insertPropInfo(info: EditPropInfoReq)

    /**
     * 更新一条道具信息
     */
    fun updatePropInfo(info: EditPropInfoReq): Boolean

    /**
     * 根据name查询所道具在id
     */
    fun findPropIdByName(name: String): Int

    /**
     * 删除道具信息
     */
    fun deletePropInfo(id: Int): Boolean

    /**
     * 查询对应的所有图鉴详情
     */
    fun findInfo(
        parentId: Int,
        name: String?,
        type: String?,
        startIndex: Int,
        pageSize: Int,
    ): MutableList<NodeInfo>?

    /**
     * 根据 name 查询所在 id
     */
    fun findIdByName(name: String): Int

    /*
    * 插入一条信息
    */
    fun insertInfo(req: UploadIllustratedInfoEntity)

    /**
     * 更新一条信息
     */
    fun updateInfo(req: UploadIllustratedInfoEntity): Boolean

    /**
     * 删除一条信息
     */
    fun deleteInfo(
        @Param("id") id: Int,
    ): Boolean
}