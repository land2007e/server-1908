package com.t3h.demoserver.database.repository

import com.t3h.demoserver.database.model.Friend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
open interface FriendRepository :
        JpaRepository<Friend?, Int?>{

    @Query(nativeQuery = true,
    value = "SELECT * FROM friend where " +
            "(friend.sender_id = :userId and friend.receiver_id = :friendId) " +
            "OR " +
            "(friend.sender_id = :friendId and friend.receiver_id = :userId)")
    fun getFriend(
            @Param(value = "userId")userId:Int,
            @Param(value = "friendId")friendId:Int
    ):MutableList<Friend>
}