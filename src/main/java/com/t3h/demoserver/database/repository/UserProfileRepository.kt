package com.t3h.demoserver.database.repository

import com.t3h.demoserver.database.model.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
open interface UserProfileRepository : JpaRepository<UserProfile?, Int?> {
    @Query(nativeQuery = true,
    value = "SELECT * FROM user_profile WHERE id = :id")
    fun getUserProfileById(
            @Param(value = "id") id:Int
    ):UserProfile?

    @Query(nativeQuery = true,
            value = "SELECT * FROM user_profile " +
                    "WHERE username = :username")
    fun getUserProfileByUsername(
            @Param(value = "username") username:String
    ):UserProfile?
}