package com.t3h.demoserver

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import kotlin.collections.HashMap

object Utils {
    @JvmStatic
    fun getJWTFromInfoUser(id: Int,
                           username: String,
                           name: String): String {
        val claims = HashMap<String, Any>()
        claims.put("username", username)
        claims.put("name", name)
        claims.put("id", id)
        return Jwts.builder().setClaims(claims)
                .setSubject(id.toString())
                .setIssuedAt(Date())
                .signWith(SignatureAlgorithm.HS512, "T3H@123")
                .setExpiration(
                        Date(System.currentTimeMillis()
                                + 60*60 * 1000L)
                )
                .compact()
    }

    @JvmStatic
    fun getUsernameFromToken(token:String):String{
        return Jwts.parser()
                .setSigningKey("T3H@123")
                .parseClaimsJws(token)
                .body.get("username", String::class.java)
    }

    @JvmStatic
    fun getIdFromToken(token:String):Int{
        return Jwts.parser()
                .setSigningKey("T3H@123")
                .parseClaimsJws(token)
                .body.subject.toInt()
    }
}