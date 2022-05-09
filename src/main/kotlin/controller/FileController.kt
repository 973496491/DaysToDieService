package com.loko.utils.controller

import com.loko.utils.base.BaseResp
import com.loko.utils.config.CodeEnum
import com.loko.utils.config.Config
import com.loko.utils.insert_entity.DllEntity
import com.loko.utils.req.UploadImageReq
import com.loko.utils.resp.FileUploadResp
import com.loko.utils.service.UploadService
import com.loko.utils.utils.CosUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.util.*


@Controller
@RequestMapping("/api/file")
@Suppress("Unused")
class FileController {

    @Autowired
    lateinit var uploadService: UploadService

    /**
     * 获取DLL列表
     */
    @RequestMapping("/dllList", method = [RequestMethod.POST])
    @ResponseBody
    fun getDllList(): BaseResp {
        val list = uploadService.findAllDll()
        return BaseResp(
            code = CodeEnum.SUCCESS.code,
            message = CodeEnum.SUCCESS.message,
            data = list,
        )
    }

    /**
     * 上传DLL
     */
    @RequestMapping("/uploadDll", method = [RequestMethod.POST], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseBody
    fun uploadDll(
        @RequestParam file: MultipartFile?,
        @RequestParam version: String,
    ): BaseResp {
        if (file == null) {
            return BaseResp(
                code = CodeEnum.FILE_IS_EMPTY.code,
                message = CodeEnum.FILE_IS_EMPTY.message,
            )
        }

        val fileName = "$version${file.originalFilename ?: "无效文件"}"
        val key = "${Config.COS_DLL_FOLDER}/$fileName"
        val tempFile = File.createTempFile("temp", null)
        file.transferTo(tempFile)

        val result = upload(tempFile, key)
        if (result.code == CodeEnum.SUCCESS.code) {
            val data = result.data as FileUploadResp
            val dllEntity = DllEntity(
                key = data.key,
                version = version,
                size = data.size,
                name = "${version}补丁",
            )
            // 先查询Dll是否存在, 存在就不插入直接返回
            if (uploadService.findDllExist(dllEntity.key).not()) {
                // 将信息插入数据库
                uploadService.insertDll(dllEntity)
                if (dllEntity.id <= 0) {
                    // 插入失败删除 cos 的文件
                    val ex = CosUtils.deleteObject(dllEntity.key)
                    return BaseResp(
                        code = CodeEnum.FILE_INSERT_FAILED.code,
                        message = ex?.message ?: CodeEnum.FILE_INSERT_FAILED.message,
                    )
                }
            }
        }

        tempFile.delete()
        return result
    }

    /**
     * 删除DLL
     */
    @RequestMapping("/deleteDll", method = [RequestMethod.POST])
    @ResponseBody
    fun deleteDll(
        @RequestParam key: String,
    ): BaseResp {
        if (uploadService.findDllExist(key)) {
            if (uploadService.deleteDll(key)) {
                return CosUtils.deleteObject(key)?.let {
                    BaseResp(
                        code = CodeEnum.FILE_DELETE_FAILED.code,
                        message = it.message ?: CodeEnum.FILE_DELETE_FAILED.message,
                    )
                } ?: BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                )
            } else {
                return BaseResp(
                    code = CodeEnum.FILE_DELETE_FAILED.code,
                    message = CodeEnum.FILE_DELETE_FAILED.message,
                )
            }
        } else {
            return BaseResp(
                code = CodeEnum.DATABASE_FILE_NOT_EXIST.code,
                message = CodeEnum.DATABASE_FILE_NOT_EXIST.message,
            )
        }
    }

    @RequestMapping("/uploadSmallMod", method = [RequestMethod.POST], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseBody
    fun uploadSmallMod(
        @RequestParam file: MultipartFile?,
    ): BaseResp {
        if (file == null) {
            return BaseResp(
                code = CodeEnum.FILE_IS_EMPTY.code,
                message = CodeEnum.FILE_IS_EMPTY.message,
            )
        }

        val fileName = file.originalFilename
        val key = "Mod_Other/$fileName"
        val tempFile = File.createTempFile("temp", null)
        file.transferTo(tempFile)

        val result = upload(tempFile, key)
        tempFile.delete()
        return result
    }

    @RequestMapping("/uploadImage", method = [RequestMethod.POST])
    @ResponseBody
    fun uploadImage(
        @RequestBody req: UploadImageReq,
    ): BaseResp {
        val imageByte = Base64.getDecoder().decode(req.fileBase64)
        val imageFile = File(req.fileName)
        val output: OutputStream = FileOutputStream(imageFile)
        val bufferedOutput = BufferedOutputStream(output)
        bufferedOutput.write(imageByte)

        val result = upload(imageFile, "${Config.COS_IMAGE_FOLDER}/${req.fileName}")
        imageFile.delete()
        return result
    }

    /**
     * 上传文件到腾讯云cos
     */
    private fun upload(
        file: File,
        key: String,
    ): BaseResp {
        // 上传文件到腾讯云cos
        return CosUtils.uploadObject(file, key)?.let {
            BaseResp(
                code = CodeEnum.FILE_UPLOAD_FAILED.code,
                message = it.message ?: CodeEnum.FILE_UPLOAD_FAILED.message,
            )
        } ?: BaseResp(
            code = CodeEnum.SUCCESS.code,
            message = CodeEnum.SUCCESS.message,
            data = FileUploadResp(
                name = file.name,
                key = key,
                size = file.length(),
            )
        )
    }
}