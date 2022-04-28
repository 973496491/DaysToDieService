package com.loko.utils.mapper

import com.loko.utils.cons.TableConst
import com.loko.utils.req.RegisterReq
import com.loko.utils.resp.Whitelist
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.springframework.web.bind.annotation.RequestBody

@Mapper
interface ConfigMapper {

    /**
     * 获取全部白名单信息
     */
    @Select("SELECT `mod_name` as `name`, mod_author as author, mod_desc as `desc` FROM `whitelist`")
    fun getAllWhitelist(): MutableList<Whitelist>?

    /**
     * 查询白名单信息是否存在
     */

    /**
     * 插入新的白名单
     */
    @Insert("insert into ${TableConst.USER} (`username`, `password`) values (#{userName}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertNewUser(
        @RequestBody user: RegisterReq,
    )
}