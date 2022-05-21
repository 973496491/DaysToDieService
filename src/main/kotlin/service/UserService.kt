package com.loko.utils.service

import com.loko.utils.req.InsertConfigReq
import com.loko.utils.req.LoginReq
import com.loko.utils.req.RegisterReq
import com.loko.utils.req.UpdateTokenReq
import com.loko.utils.entity.resp.UserInfoResp

interface UserService {

    /**
     * 根据用户名获取用户数量
     */
    fun getUserCount(userName: String): Int

    /**
     * 插入新用户
     */
    fun insertNewUser(user: RegisterReq)

    /**
     * 检查用户密码是否正确
     */
    fun checkedUserPassword(user: LoginReq): Boolean

    /**
     * 获取用户信息
     */
    fun getUserInfoByToken(token: String): UserInfoResp?

    /**
     * 根据账号密码查询用户Id
     */
    fun findUserIdByAccount(userName: String, password: String): Int

    /**
     * 根据Token查询用户Id
     */
    fun getUserIdByToken(token: String): Int

    /**
     * 根据用户Id查询Token
     */
    fun findTokenByUserId(userId: Int): String?

    /**
     * 插入Token
     */
    fun insertToken(req: UpdateTokenReq): Boolean

    /**
     * 更新Token
     */
    fun updateToken(req: UpdateTokenReq): Boolean

    /**
     * 查询是否有用户配置
     */
    fun hasUserConfig(userId: Int): Boolean

    /**
     * 插入用户配置
     */
    fun insertUserConfig(req: InsertConfigReq)

    /**
     * 更新用户配置
     */
    fun updateUserConfig(req: InsertConfigReq): Boolean

    /**
     * 更新用户下载次数
     */
    fun updateDownloadCount(userId: Int, count: Int): Boolean
}