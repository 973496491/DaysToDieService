package com.loko.utils

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
open class ApplicationRunnerImpl : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        println("Spring Boot 启动成功了 ~")
    }
}