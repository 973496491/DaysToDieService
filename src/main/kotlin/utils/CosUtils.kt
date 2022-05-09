package com.loko.utils.utils

import com.loko.utils.config.Config
import com.qcloud.cos.COSClient
import com.qcloud.cos.ClientConfig
import com.qcloud.cos.auth.BasicCOSCredentials
import com.qcloud.cos.model.PutObjectRequest
import com.qcloud.cos.region.Region
import java.io.File

/**
 * 腾讯云Cos辅助类
 */
object CosUtils {

    /**
     * 获取Cos客户端
     */
    fun getCosClient(): COSClient {
        val cred = BasicCOSCredentials(Config.COS_SECRET_ID, Config.COS_SECRET_KEY)
        val region = Region(Config.COS_REGION)
        val clientConfig = ClientConfig(region)
        return COSClient(cred, clientConfig)
    }

    /**
     * 上传对象
     */
    fun uploadObject(file: File, key: String): Throwable? {
        val client = getCosClient()
        return kotlin.runCatching {
            val putObjectRequest = PutObjectRequest(Config.COS_BUCKET_NAME, key, file)
            client.putObject(putObjectRequest)
        }.fold(
            {
                client.shutdown()
                null
            }, {
                client.shutdown()
                it
            }
        )
    }

    /**
     * 删除对象
     */
    fun deleteObject(key: String): Throwable? {
        val client = getCosClient()
        return kotlin.runCatching {
            client.deleteObject(Config.COS_BUCKET_NAME, key)
        }.fold(
            {
                client.shutdown()
                null
            }, {
                client.shutdown()
                it
            }
        )
    }
}