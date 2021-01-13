package com.t3h.demoserver.database.repository.response

import com.t3h.demoserver.model.response.NotFriendResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
open interface NotFriendResponseRepository :
        JpaRepository<NotFriendResponse?, Int?> {
    @Query(nativeQuery = true,
            value = "SELECT " +
                    "user_profile.id, " +
                    "user_profile.username, " +
                    "user_profile.name, " +
                    "user_profile.avatar " +
                    "FROM user_profile " +
                    "LEFT JOIN friend ON " +
                    "(user_profile.id = friend.receiver_id OR user_profile.id = friend.sender_id) " +
                    "AND " +
                    "(friend.receiver_id = :userId OR friend.sender_id = :userId) " +
                    "WHERE friend.id is null AND user_profile.id <> :userId"
    )
    fun getNotFriends(
            @Param(value = "userId") userId: Int
    ): MutableList<NotFriendResponse>


    @Query(nativeQuery = true,
            value = "SELECT " +
                    "user_profile.id, " +
                    "user_profile.username, " +
                    "user_profile.name, " +
                    "user_profile.avatar " +
                    "FROM user_profile " +
                    "LEFT JOIN friend ON " +
                    "(user_profile.id = friend.receiver_id OR user_profile.id = friend.sender_id) " +
                    "AND " +
                    "(friend.receiver_id = :userId OR friend.sender_id = :userId) " +
                    "WHERE friend.id is not null AND user_profile.id <> :userId"
    )
    fun getFriends(
            @Param(value = "userId")userId:Int
    ):MutableList<NotFriendResponse>
}