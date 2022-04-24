package com.loko.utils.service

import com.loko.utils.mapper.UserMapper
import com.loko.utils.req.InsertConfigReq
import com.loko.utils.req.LoginReq
import com.loko.utils.req.RegisterReq
import com.loko.utils.req.UpdateTokenReq
import com.loko.utils.resp.UserInfoResp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.interceptor.TransactionAspectSupport

@Suppress("Unused")
@Service
open class UserServiceImpl : UserService {

    @Autowired
    lateinit var userMapper: UserMapper

    override fun getUserCount(userName: String): Int {
        return userMapper.getUserCount(userName)
    }

    override fun insertNewUser(user: RegisterReq) {
        return userMapper.insertNewUser(user)
    }

    override fun checkedUserPassword(user: LoginReq): Boolean {
        return userMapper.checkedUserPassword(user) > 0
    }

    override fun getUserInfoByToken(token: String): UserInfoResp? {
        return userMapper.getUserInfoByToken(token)?.firstOrNull()
    }

    override fun findUserIdByAccount(userName: String, password: String): Int {
        return userMapper.findUserIdByAccount(userName, password) ?: -1
    }

    override fun getUserIdByToken(token: String): Int {
        return userMapper.getUserIdByToken(token)
    }

    override fun findTokenByUserId(userId: Int): String? {
        return userMapper.findTokenByUserId(userId)
    }

    override fun insertToken(req: UpdateTokenReq): Boolean {
        return userMapper.insertToken(req) > 0
    }

    override fun updateToken(req: UpdateTokenReq): Boolean {
        return userMapper.updateToken(req) > 0
    }

    override fun hasUserConfig(userId: Int): Boolean {
        return userMapper.hasUserConfig(userId) > 0
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun insertUserConfig(req: InsertConfigReq) {
        kotlin.runCatching {
            userMapper.insertUserConfig(req)
        }.onFailure {
            it.printStackTrace()
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
        }
    }

    override fun updateUserConfig(req: InsertConfigReq): Boolean {
        return userMapper.updateUserConfig(req) > 0
    }

    override fun updateDownloadCount(userId: Int, count: Int): Boolean {
        return userMapper.updateDownloadCount(userId, count) > 0
    }
}