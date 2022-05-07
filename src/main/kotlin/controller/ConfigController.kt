package com.loko.utils.controller

import com.loko.utils.base.BaseResp
import com.loko.utils.cons.CodeEnum
import com.loko.utils.req.XmlContentReq
import com.loko.utils.resp.XmlInfoResp
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

    @PostMapping("/xmlcontent")
    @ResponseBody
    fun getXmlDataForKey(
        @RequestBody req: XmlContentReq,
    ): BaseResp {
        val keyInfo = configService.getKeyForId(req.key) ?: return BaseResp(
            code = CodeEnum.KEY_INFO_NOT_FOUND.code,
            message = CodeEnum.KEY_INFO_NOT_FOUND.message,
        )

        val keyId = keyInfo.id
        val xmlList = configService.getXmlDataForKey(keyId, req.modName, req.modAuthor)
        return if (xmlList == null) {
            BaseResp(
                code = CodeEnum.XML_INFO_IS_NULL.code,
                message = CodeEnum.XML_INFO_IS_NULL.message,
            )
        } else {
            if (xmlList.isNotEmpty()) {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                    data = XmlInfoResp(
                        publicKey = keyInfo.key,
                        iv = keyInfo.iv,
                        xmlList = xmlList
                    ),
                )
            } else {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            }
        }
    }
}