package com.loko.utils.service

import com.loko.utils.resp.Whitelist
import org.springframework.stereotype.Service

@Service
interface ConfigService {

    fun getAllWhitelist(): MutableList<Whitelist>?
}