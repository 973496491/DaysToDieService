package com.loko.utils.utils

import com.loko.utils.cons.CodeEnum
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*


object JWTUtils {

    private const val SECRET = "7daystodieutilsspringboot"

    /**
     * 初始化生成token的参数
     * @param userId
     * @return String
     */
    fun generateToken(userId: Int): String? {
        val claims: MutableMap<String, Any> = HashMap(1)
        claims["sub"] = userId
        return generateToken(claims)
    }

    /**
     * 生成token
     * @param claims
     * @return String
     */
    private fun generateToken(claims: Map<String, Any>): String? {
        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateExpirationDate())
            .setIssuedAt(generateCurrentDate())
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()
    }

    /**
     * 校验 Token 是否有效
     */
    fun verifyToken(token: String?): CodeEnum {
        return kotlin.runCatching {
            Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .body
        }.fold(
            {
                CodeEnum.SUCCESS
            },
            {
                CodeEnum.TOKEN_INVALID
            }
        )
    }

    private fun generateExpirationDate(): Date {
        val calendar = System.currentTimeMillis().toCalendar()
        calendar.add(Calendar.DATE, 7)
        return calendar.time
    }

    private fun generateCurrentDate(): Date {
        return System.currentTimeMillis().toDate()
    }
}