package com.loko.utils.service_impl

import com.loko.utils.insert_entity.DllEntity
import com.loko.utils.mapper.UploadMapper
import com.loko.utils.service.UploadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Suppress("Unused")
@Service
class UploadServiceImpl : UploadService {

    @Autowired
    lateinit var uploadMapper: UploadMapper

    override fun insertDll(entity: DllEntity) {
        uploadMapper.insertDll(entity)
    }

    override fun findDllExist(key: String): Boolean {
        return uploadMapper.findDllExist(key) > 0
    }

    override fun deleteDll(key: String): Boolean {
        return uploadMapper.deleteDll(key) > 0
    }

    override fun findAllDll(): List<DllEntity>? {
        return uploadMapper.findAllDll() ?: mutableListOf()
    }
}