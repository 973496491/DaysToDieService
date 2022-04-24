package com.loko.utils.mapper

import com.loko.utils.cons.TableConst
import com.loko.utils.insert_entity.DllEntity
import org.apache.ibatis.annotations.*
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Mapper
interface UploadMapper {

    /**
     * 插入DLL
     */
    @Insert("insert into ${TableConst.DLL} (`key`, `version`, `size`, `name`) values (#{key}, #{version}, #{size}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertDll(
        @RequestBody entity: DllEntity
    )

    /**
     * 查询Dll是否存在
     */
    @Select("select count(*) from ${TableConst.DLL} where `key` = #{key}")
    fun findDllExist(
        @RequestParam key: String
    ): Int

    /**
     * 删除DLL
     */
    @Delete("delete from ${TableConst.DLL} where `key` = #{key}")
    fun deleteDll(
        @RequestParam key: String
    ): Int

    /**
     * 查询DLL列表
     */
    @Select("select * from ${TableConst.DLL}")
    fun findAllDll(): List<DllEntity>?
}
