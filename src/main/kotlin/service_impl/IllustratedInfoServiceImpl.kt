package com.loko.utils.service_impl

import com.loko.utils.entity.resp.*
import com.loko.utils.insert_entity.UploadIllustratedInfoEntity
import com.loko.utils.mapper.IllustratedInfoMapper
import com.loko.utils.req.EditPropInfoReq
import com.loko.utils.req.EditZombieInfoReq
import com.loko.utils.service.IllustratedInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IllustratedInfoServiceImpl : IllustratedInfoService {

    @Autowired
    lateinit var illustratedInfoMapper: IllustratedInfoMapper

    override fun findAllMenu(): MutableList<MenuInfo>? {
        return illustratedInfoMapper.findAllMenu()
    }

    override fun getZombieList(
        name: String?,
        type: String?,
        startIndex: Int,
        pageSize: Int
    ): MutableList<ZombieNodeResp>? {
        return illustratedInfoMapper.getZombieList(name, type, startIndex, pageSize)
    }

    override fun getZombieInfo(id: Int): ZombieInfoResp? {
        return illustratedInfoMapper.getZombieInfo(id)
    }

    override fun insertZombieInfo(info: EditZombieInfoReq) {
        return illustratedInfoMapper.insertZombieInfo(info)
    }

    override fun updateZombieInfo(info: EditZombieInfoReq): Boolean {
        return illustratedInfoMapper.updateZombieInfo(info) > 0
    }

    override fun findZombieIdByName(name: String): Int {
        return illustratedInfoMapper.findZombieIdByName(name) ?: -1
    }

    override fun deleteZombieInfo(id: Int): Boolean {
        return illustratedInfoMapper.deleteZombieInfo(id) > 0
    }

    override fun getPropList(
        name: String?,
        type: String?,
        startIndex: Int,
        pageSize: Int
    ): MutableList<PropNodeResp>? {
        return illustratedInfoMapper.getPropList(name, type, startIndex, pageSize)
    }

    override fun getPropInfo(id: Int): PropInfoResp? {
        return illustratedInfoMapper.getPropInfo(id)
    }

    override fun insertPropInfo(info: EditPropInfoReq) {
        return illustratedInfoMapper.insertPropInfo(info)
    }

    override fun updatePropInfo(info: EditPropInfoReq): Boolean {
        return illustratedInfoMapper.updatePropInfo(info) > 0
    }

    override fun findPropIdByName(name: String): Int {
        return illustratedInfoMapper.findPropIdByName(name) ?: -1
    }

    override fun deletePropInfo(id: Int): Boolean {
        return illustratedInfoMapper.deletePropInfo(id) > 0
    }

    override fun findInfo(
        parentId: Int,
        name: String?,
        type: String?,
        startIndex: Int,
        pageSize: Int,
    ): MutableList<NodeInfo>? {
        return illustratedInfoMapper.findInfo(parentId, name, type, startIndex, pageSize)
    }

    override fun findIdByName(name: String): Int {
        return illustratedInfoMapper.findIdByName(name) ?: -1
    }

    override fun insertInfo(req: UploadIllustratedInfoEntity) {
        illustratedInfoMapper.insertInfo(req)
    }

    override fun updateInfo(req: UploadIllustratedInfoEntity): Boolean {
        return illustratedInfoMapper.updateInfo(req) > 0
    }

    override fun deleteInfo(id: Int): Boolean {
        return illustratedInfoMapper.deleteInfo(id) > 0
    }
}