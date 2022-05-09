package com.loko.utils.controller

import com.loko.utils.base.BaseResp
import com.loko.utils.config.CodeEnum
import com.loko.utils.req.*
import com.loko.utils.resp.UserTokenResp
import com.loko.utils.service.UserService
import com.loko.utils.utils.JWTUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api/users")
@Suppress("Unused")
open class UserController {

    @Autowired
    lateinit var userService: UserService

    /**
     * 注册
     */
    @RequestMapping("/register", method = [RequestMethod.POST])
    @ResponseBody
    open fun register(
        @RequestBody user: RegisterReq,
    ): BaseResp? {
        with(user) {
            val hasUser = userService.getUserCount(userName) > 0
            if (hasUser) {
                return BaseResp(
                    code = CodeEnum.REGISTERED.code,
                    message = CodeEnum.REGISTERED.message,
                    data = null
                )
            }

            userService.insertNewUser(this)

            if (user.id <= 0) {
                return BaseResp(
                    code = CodeEnum.REGISTER_FAILED.code,
                    message = CodeEnum.REGISTER_FAILED.message,
                    data = null,
                )
            }

            val configReq = InsertConfigReq(
                userId = user.id,
                gamePath = "",
                token = "",
            )
            userService.insertUserConfig(configReq)
            if (configReq.id <= 0) {
                return BaseResp(
                    code = CodeEnum.USER_CONFIG_FAILED.code,
                    message = CodeEnum.USER_CONFIG_FAILED.message,
                    data = null,
                )
            }

            return BaseResp(
                code = CodeEnum.REGISTER_SUCCESS.code,
                message = CodeEnum.REGISTER_SUCCESS.message,
                data = CodeEnum.REGISTER_SUCCESS.message,
            )
        }
    }

    /**
     * 登录
     */
    @RequestMapping("/login", method = [RequestMethod.POST])
    @ResponseBody
    open fun login(
        @RequestBody loginReq: LoginReq,
    ): BaseResp? {
        with(loginReq) {
            val hasUser = userService.getUserCount(userName) > 0
            if (!hasUser) {
                return BaseResp(
                    code = CodeEnum.NOT_USER.code,
                    message = CodeEnum.NOT_USER.message,
                    data = null
                )
            }

            val userId = userService.findUserIdByAccount(userName, password)
            if (userId <= 0) {
                return BaseResp(
                    code = CodeEnum.PASSWORD_FAILED.code,
                    message = CodeEnum.PASSWORD_FAILED.message,
                    data = null,
                )
            }

            // 根据用户id查询token
            var token = userService.findTokenByUserId(userId)

            if (token.isNullOrEmpty()) {
                token = insertToken(userId)
            } else {
                val tokenChecked = JWTUtils.verifyToken(token)
                if (tokenChecked == CodeEnum.TOKEN_INVALID) {
                    token = JWTUtils.generateToken(userId)?.let {
                        val req = UpdateTokenReq(
                            userId = userId,
                            token = it
                        )
                        val isSuc = userService.updateToken(req)
                        if (isSuc) it
                        else null
                    }
                }
            }

            if (token == null) {
                return BaseResp(
                    code = CodeEnum.TOKEN_GENERATE_FAILED.code,
                    message = CodeEnum.TOKEN_GENERATE_FAILED.message,
                    data = null,
                )
            }

            // 返回token
            val userTokenResp = UserTokenResp(token)
            return BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
                data = userTokenResp,
            )
        }
    }

    private fun insertToken(userId: Int): String? {
        val currentToken = JWTUtils.generateToken(userId) ?: return null
        val req = UpdateTokenReq(
            userId = userId,
            token = currentToken
        )
        val isSuc = userService.insertToken(req)
        return if (isSuc) {
            currentToken
        } else {
            null
        }
    }

//    /**
//     * 更新下载次数
//     */
//    @RequestMapping("/updateDownloadCount", method = [RequestMethod.POST])
//    @ResponseBody
//    fun updateDownloadCount(
//        @RequestBody req: AddDownloadCountReq
//    ) {
//
//    }

    /**
     * 更新用户配置
     */
    @RequestMapping("/updateUserConfig", method = [RequestMethod.POST])
    @ResponseBody
    fun updateUserConfig(
        @RequestBody req: InsertConfigReq
    ): BaseResp {
        var isSuc: Boolean
        with(req) {
            val userId = userService.getUserIdByToken(token)
            req.userId = userId
            val hasConfig = userService.hasUserConfig(userId)
            isSuc = if (hasConfig) {
                userService.updateUserConfig(this)
            } else {
                userService.insertUserConfig(this)
                req.id > 0
            }

            return if (isSuc) {
                BaseResp(
                    code = CodeEnum.SUCCESS.code,
                    message = CodeEnum.SUCCESS.message,
                    data = null,
                )
            } else {
                BaseResp(
                    code = CodeEnum.FAILED.code,
                    message = CodeEnum.FAILED.message,
                    data = null,
                )
            }
        }
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/getUserInfo", method = [RequestMethod.POST])
    @ResponseBody
    fun getUserInfo(
        @RequestBody req: GetUserInfoReq,
    ): BaseResp {
        return userService.getUserInfoByToken(req.token)?.let {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
                data = it,
            )
        } ?: BaseResp(
            code = CodeEnum.USERINFO_NOTHING.code,
            message = CodeEnum.USERINFO_NOTHING.message,
            data = null,
        )
    }

    /**
     * 扣除一次下载次数
     */
    @RequestMapping("/subtractDownloadCount", method = [RequestMethod.POST])
    @ResponseBody
    fun subtractDownloadCount(
        @RequestBody req: SubtractDownloadCountReq,
    ): BaseResp {
        return updateDownloadCount(
            UpdateDownloadCountReq(
                req.token,
                -1
            )
        )
    }

    /**
     * 更新用户下载次数
     */
    @RequestMapping("/updateDownloadCount", method = [RequestMethod.POST])
    @ResponseBody
    fun updateDownloadCount(
        @RequestBody req: UpdateDownloadCountReq,
    ): BaseResp {
        val userId = userService.getUserIdByToken(req.token)
        val isSuc = userService.updateDownloadCount(userId, req.count)
        return if (isSuc) {
            BaseResp(
                code = CodeEnum.SUCCESS.code,
                message = CodeEnum.SUCCESS.message,
            )
        } else {
            BaseResp(
                code = CodeEnum.FAILED.code,
                message = CodeEnum.FAILED.message,
            )
        }
    }
}