package com.loko.utils.controller

import com.loko.utils.base.BaseResp
import com.loko.utils.config.CodeEnum
import com.loko.utils.entity.common.Whitelist
import com.loko.utils.entity.req.XmlContentReq
import com.loko.utils.service.ConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/config")
@Suppress("Unused")
class ConfigController {

    @Autowired
    lateinit var configService: ConfigService

    /**
     * 获取白名单列表
     */
    @GetMapping("/whitelist")
    @ResponseBody
    fun getWhitelist(): BaseResp {
        val list = configService.getAllWhitelist()
        return BaseResp(
            code = CodeEnum.SUCCESS.code,
            message = CodeEnum.SUCCESS.message,
            data = list,
        )
    }

    @PostMapping("/add/whitelist")
    @ResponseBody
    fun addWhitelistItem(
        @RequestBody item: Whitelist
    ): BaseResp {
        val id = configService.getWhitelistItemId(item.name, item.author)
        return if (id > 0) {
            val newItem = item.copy(id = id)
            if (configService.updateWhitelistItem(newItem)) {
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
            if (configService.insertWhitelistItem(item)) {
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
        }
    }

    @PostMapping("/xmlcontent")
    @ResponseBody
    fun getXmlDataForKey(
        @RequestBody req: XmlContentReq,
    ): BaseResp {
        val keyInfo = configService.getXmlContentFromSteamId(req.steamId, req.modKey)
        return BaseResp(
            code = CodeEnum.SUCCESS.code,
            message = CodeEnum.SUCCESS.message,
            data = keyInfo
        )
    }
}