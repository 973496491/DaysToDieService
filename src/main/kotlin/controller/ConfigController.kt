package com.loko.utils.controller

import com.loko.utils.base.BaseResp
import com.loko.utils.cons.CodeEnum
import com.loko.utils.service.ConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api/config")
@Suppress("Unused")
class ConfigController {

    @Autowired
    lateinit var configService: ConfigService

    /**
     * 获取DLL列表
     */
    @RequestMapping("/whitelist", method = [RequestMethod.POST])
    @ResponseBody
    fun getWhitelist(): BaseResp {
        val list = configService.getAllWhitelist()
        return BaseResp(
            code = CodeEnum.SUCCESS.code,
            message = CodeEnum.SUCCESS.message,
            data = list,
        )
    }
}