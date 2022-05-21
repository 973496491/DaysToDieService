package com.loko.utils.controller

import com.loko.utils.base.BaseResp
import com.loko.utils.config.CodeEnum
import com.loko.utils.insert_entity.UploadIllustratedInfoEntity
import com.loko.utils.req.*
import com.loko.utils.entity.resp.IllustratedInfo
import com.loko.utils.entity.resp.IllustratedInfoResp
import com.loko.utils.service.IllustratedInfoService
import com.loko.utils.utils.safeToInt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api/illustratedInfo")
@Suppress("Unused")
open class IllustratedInfoController {

    @Autowired
    lateinit var illustratedInfoService: IllustratedInfoService

    /**
     * 查询所有的古神列表
     */
    @RequestMapping(
        "/getAllZombieList",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun getZombieList(
        @RequestBody req: GetZombieListReq,
    ): BaseResp {
        val index = req.pageIndex.safeToInt() - 1
        val startIndex = if ((index) < 0) 0 else index * req.pageSize.safeToInt()
        val list = illustratedInfoService.getZombieList(req.name, req.type, startIndex, req.pageSize)
        return BaseResp(
            code = CodeEnum.SUCCESS.code,
            message = CodeEnum.SUCCESS.message,
            data = list
        )
    }

    /**
     * 查询古神信息
     */
    @RequestMapping(
        "/getZombieInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun getZombieInfo(
        @RequestBody req: GetZombieInfoReq,
    ): BaseResp {
        return illustratedInfoService.getZombieInfo(req.id)?.let {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
                data = it
            )
        } ?: BaseResp(
            code = CodeEnum.FAILED.code,
            message = CodeEnum.FAILED.message
        )
    }

    /**
     * 插入一条古神信息
     */
    @RequestMapping(
        "/insertZombieInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun insertZombieInfo(
        @RequestBody req: EditZombieInfoReq,
    ): BaseResp {
        val insertReq = req.copy()

        val id = illustratedInfoService.findZombieIdByName(req.name)
        if (id > 0) {
            // 更新信息
            val isSuc = illustratedInfoService.updateZombieInfo(insertReq)
            return if (isSuc) {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } else {
                BaseResp(
                    code = CodeEnum.UPDATE_ZOMBIE_FAILED.code,
                    message = CodeEnum.UPDATE_ZOMBIE_FAILED.message,
                )
            }
        } else {
            // 插入信息
            illustratedInfoService.insertZombieInfo(insertReq)
            return insertReq.id?.let {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } ?: BaseResp(
                code = CodeEnum.INSERT_ZOMBIE_FAILED.code,
                message = CodeEnum.INSERT_ZOMBIE_FAILED.message
            )
        }
    }

    /**
     * 更新一条古神信息
     */
    @RequestMapping(
        "/updateZombieInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun updateZombieInfo(
        @RequestBody req: EditZombieInfoReq,
    ): BaseResp {
        // 更新信息
        val isSuc = illustratedInfoService.updateZombieInfo(req)
        return if (isSuc) {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
            )
        } else {
            BaseResp(
                code = CodeEnum.UPDATE_ZOMBIE_FAILED.code,
                message = CodeEnum.UPDATE_ZOMBIE_FAILED.message,
            )
        }
    }

    /**
     * 删除古神
     */
    @RequestMapping(
        "/deleteZombie",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun deleteZombie(
        @RequestBody req: DeleteZombieReq,
    ): BaseResp {
        return if (illustratedInfoService.deleteZombieInfo(req.id)) {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
            )
        } else {
            BaseResp(
                code = CodeEnum.DELETE_ZOMBIE_FAILED.code,
                message = CodeEnum.DELETE_ZOMBIE_FAILED.message
            )
        }
    }

    /**
     * 查询所有的道具列表
     */
    @RequestMapping(
        "/getAllPropList",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun getPropList(
        @RequestBody req: GetPropListReq,
    ): BaseResp {
        val index = req.pageIndex.safeToInt() - 1
        val startIndex = if ((index) < 0) 0 else index * req.pageSize.safeToInt()
        val list = illustratedInfoService.getPropList(req.name, req.type, startIndex, req.pageSize)
        return BaseResp(
            code = CodeEnum.SUCCESS.code,
            message = CodeEnum.SUCCESS.message,
            data = list
        )
    }

    /**
     * 查询道具信息
     */
    @RequestMapping(
        "/getPropInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun getPropInfo(
        @RequestBody req: GetPropInfoReq,
    ): BaseResp {
        return illustratedInfoService.getPropInfo(req.id)?.let {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
                data = it
            )
        } ?: BaseResp(
            code = CodeEnum.FAILED.code,
            message = CodeEnum.FAILED.message
        )
    }

    /**
     * 插入一条道具信息
     */
    @RequestMapping(
        "/insertPropInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun insertPropInfo(
        @RequestBody req: EditPropInfoReq,
    ): BaseResp {
        val propId = illustratedInfoService.findPropIdByName(req.name)
        val insertReq = req.copy(
            id = propId
        )
        if (propId > 0) {
            // 更新信息
            val isSuc = illustratedInfoService.updatePropInfo(insertReq)
            return if (isSuc) {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } else {
                BaseResp(
                    code = CodeEnum.UPDATE_PROP_FAILED.code,
                    message = CodeEnum.UPDATE_PROP_FAILED.message,
                )
            }
        } else {
            // 插入信息
            illustratedInfoService.insertPropInfo(insertReq)
            return insertReq.id?.let {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } ?: BaseResp(
                code = CodeEnum.INSERT_PROP_FAILED.code,
                message = CodeEnum.INSERT_PROP_FAILED.message
            )
        }
    }

    /**
     * 更新一条道具信息
     */
    @RequestMapping(
        "/updatePropInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun updatePropInfo(
        @RequestBody req: EditPropInfoReq,
    ): BaseResp {
        // 更新信息
        val isSuc = illustratedInfoService.updatePropInfo(req)
        return if (isSuc) {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
            )
        } else {
            BaseResp(
                code = CodeEnum.UPDATE_PROP_FAILED.code,
                message = CodeEnum.UPDATE_PROP_FAILED.message,
            )
        }
    }

    /**
     * 删除道具
     */
    @RequestMapping(
        "/deleteProp",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun deleteProp(
        @RequestBody req: DeletePropReq,
    ): BaseResp {
        return if (illustratedInfoService.deletePropInfo(req.id)) {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
            )
        } else {
            BaseResp(
                code = CodeEnum.DELETE_PROP_FAILED.code,
                message = CodeEnum.DELETE_PROP_FAILED.message
            )
        }
    }

    /**
     * 获取图鉴信息
     */
    @RequestMapping(
        "/getAllInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun getAllInfo(
        @RequestBody req: IllustratedInfoReq,
    ): BaseResp {
        val resp = IllustratedInfoResp(mutableListOf())
        return illustratedInfoService.findAllMenu()?.let {
            it.forEach { menu ->
                val index = req.pageIndex.safeToInt() - 1
                val startIndex = if ((index) < 0) 0 else index * req.pageSize.safeToInt()
                val pageSize = req.pageSize.safeToInt()
                val list = illustratedInfoService.findInfo(menu.id, req.name, req.type, startIndex, pageSize)
                    ?: mutableListOf()
                resp.list?.add(
                    IllustratedInfo(
                        id = menu.id,
                        menuName = menu.menuName,
                        itemNode = list,
                    )
                )
            }
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
                data = resp
            )
        } ?: BaseResp(
            code = CodeEnum.GET_ILLUSTRATED_INFO_GROUP_FAILED.code,
            message = CodeEnum.GET_ILLUSTRATED_INFO_GROUP_FAILED.message,
            data = resp
        )
    }

    /**
     * 更新一条图鉴
     */
    @RequestMapping(
        "/uploadInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun uploadInfo(
        @RequestBody req: UploadIllustratedInfoReq,
    ): BaseResp? {
        val insertReq = UploadIllustratedInfoEntity(
            parentId = req.parentId,
            type = req.type,
            name = req.name,
            imageKey = req.imageKey,
            content = req.content,
        )

        val id = illustratedInfoService.findIdByName(insertReq.name)
        if (id > 0) {
            insertReq.id = id
            val isSuc = illustratedInfoService.updateInfo(insertReq)
            return if (isSuc) {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } else {
                BaseResp(
                    code = CodeEnum.UPDATE_ILLUSTRATED_INFO_FAILED.code,
                    message = CodeEnum.UPDATE_ILLUSTRATED_INFO_FAILED.message,
                )
            }
        } else {
            illustratedInfoService.insertInfo(insertReq)
            return if (insertReq.id > 0) {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } else {
                BaseResp(
                    code = CodeEnum.INSERT_ILLUSTRATED_INFO_FAILED.code,
                    message = CodeEnum.INSERT_ILLUSTRATED_INFO_FAILED.message,
                )
            }
        }
    }

    /**
     * 删除一条图鉴
     */
    @RequestMapping(
        "/deleteInfo",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    open fun deleteInfo(
        @RequestBody req: DeleteIllustratedInfoReq
    ): BaseResp {
        val id = illustratedInfoService.findIdByName(req.name)
        return if (id > 0) {
            val isSuc = illustratedInfoService.deleteInfo(id)
            if (isSuc) {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } else {
                BaseResp(
                    code = CodeEnum.FAILED.code,
                    message = CodeEnum.FAILED.message,
                )
            }
        } else {
            BaseResp(
                code = CodeEnum.ILLUSTRATED_INFO_UNKNOWN.code,
                message = CodeEnum.ILLUSTRATED_INFO_UNKNOWN.message,
            )
        }
    }
}