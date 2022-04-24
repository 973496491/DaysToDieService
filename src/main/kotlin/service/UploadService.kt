package com.loko.utils.service

import com.loko.utils.insert_entity.DllEntity
import org.springframework.stereotype.Service

@Service
interface UploadService {

    /**
     * 插入Dll信息
     */
    fun insertDll(entity: DllEntity)

    /**
     * 查询Dll是否存在
     */
    fun findDllExist(key: String): Boolean

    /**
     * 删除Dll
     */
    fun deleteDll(key: String): Boolean

    /**
     * 查询所有DLL
     */
    fun findAllDll(): List<DllEntity>?
}