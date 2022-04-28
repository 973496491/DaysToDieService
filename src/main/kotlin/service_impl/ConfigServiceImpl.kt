package com.loko.utils.service_impl

import com.loko.utils.mapper.ConfigMapper
import com.loko.utils.resp.Whitelist
import com.loko.utils.service.ConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConfigServiceImpl : ConfigService {

    @Autowired
    private lateinit var configMapper: ConfigMapper

    override fun getAllWhitelist(): MutableList<Whitelist>? {
        return configMapper.getAllWhitelist()
    }
}