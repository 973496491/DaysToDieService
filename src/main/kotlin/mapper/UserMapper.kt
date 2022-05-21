package com.loko.utils.mapper

import com.loko.utils.config.TableConst
import com.loko.utils.req.InsertConfigReq
import com.loko.utils.req.LoginReq
import com.loko.utils.req.RegisterReq
import com.loko.utils.req.UpdateTokenReq
import com.loko.utils.entity.resp.UserInfoResp
import org.apache.ibatis.annotations.*
import org.springframework.web.bind.annotation.RequestBody

@Mapper
interface UserMapper {

    /**
     * 查询当前用户是否存在
     */
    @Select("select count(*) from ${TableConst.USER} where username = #{username}")
    fun getUserCount(
        @Param("username") userName: String,
    ): Int

    /**
     * 插入新用户
     */
    @Insert("insert into ${TableConst.USER} (`username`, `password`) values (#{userName}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertNewUser(
        @RequestBody user: RegisterReq,
    )

    /**
     * 检查用户密码是否错误
     */
    @Select("select count(*) from ${TableConst.USER} where username = #{userName} and password=#{password} limit 0,1")
    fun checkedUserPassword(
        @RequestBody user: LoginReq,
    ): Int

    /**
     * 根据token查询用户配置信息
     */
    @Select("SELECT " +
                "a.username as userName, " +
                "b.game_path as gamePath, " +
                "b.is_admin as admin, " +
                "b.fast_download_count as fastDownloadCount " +
            "FROM " +
                "`${TableConst.USER}` AS a " +
            "LEFT JOIN `${TableConst.USER_CONFIG}` AS b ON a.id = b.user_id " +
            "LEFT JOIN `${TableConst.USER_TOKEN}` AS c ON c.user_id = a.id " +
            "WHERE " +
                "c.token = #{token}"
    )
    fun getUserInfoByToken(
        @Param("token") token: String,
    ): MutableList<UserInfoResp>?

    /**
     * 通过Token查UserId
     */
    @Select("select user_id from ${TableConst.USER_TOKEN} where token = #{token}")
    fun getUserIdByToken(
        @Param("token") token: String
    ): Int

    /**
     * 根据用户Id查询Token
     */
    @Select("select token from ${TableConst.USER_TOKEN} where user_id = #{userId}")
    fun findTokenByUserId(
        @Param("userId") userId: Int
    ): String?

    /**
     * 根据账号密码查询用户id
     */
    @Select("select id from ${TableConst.USER} where username = #{user_name} and password = #{password}")
    fun findUserIdByAccount(
        @Param("user_name") userName: String,
        @Param("password") password: String,
    ): Int?

    /**
     * 插入Token
     */
    @Insert("insert into ${TableConst.USER_TOKEN} (user_id, token) values (#{userId}, #{token})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertToken(
        @RequestBody req: UpdateTokenReq,
    ): Int

    /**
     * 更新Token
     */
    @Update("update ${TableConst.USER_TOKEN} set token = #{token} where user_id = #{userId}")
    fun updateToken(
        @RequestBody req: UpdateTokenReq,
    ): Int

    /**
     * 通过userId查询是否有用户配置
     */
    @Select("select count(*) from ${TableConst.USER_CONFIG} where user_id = #{user_id}")
    fun hasUserConfig(
        @Param("user_id") userId: Int
    ): Int

    /**
     * 插入一条用户配置信息
     */
    @Insert("insert into ${TableConst.USER_CONFIG} (`user_id`, `game_path`) values (#{userId}, #{gamePath})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertUserConfig(
        @RequestBody req: InsertConfigReq,
    )

    /**
     * 更新一条用户配置信息
     */
    @Update("update ${TableConst.USER_CONFIG} set game_path = #{gamePath} where user_id = #{userId}")
    fun updateUserConfig(
        @RequestBody req: InsertConfigReq,
    ): Int

    /**
     * 更新下载次数
     */
    @Update("update ${TableConst.USER_CONFIG} set `fast_download_count` = #{count} + (`fast_download_count`)  where `user_id` = #{userId}")
    fun updateDownloadCount(
        @Param("userId") userId: Int,
        @Param("count") count: Int,
    ): Int
}