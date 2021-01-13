package com.t3h.demoserver.controller

import com.t3h.demoserver.Utils
import com.t3h.demoserver.database.model.Friend
import com.t3h.demoserver.database.model.UserProfile
import com.t3h.demoserver.database.repository.FriendRepository
import com.t3h.demoserver.database.repository.UserProfileRepository
import com.t3h.demoserver.database.repository.response.NotFriendResponseRepository
import com.t3h.demoserver.model.CommonResponse
import com.t3h.demoserver.model.request.LoginRequest
import com.t3h.demoserver.model.response.LoginResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
open class UserController {
    //chui trong do lay
    @Autowired
    private lateinit var userProfile: UserProfileRepository

    @Autowired
    private lateinit var notFriendResponseRepository: NotFriendResponseRepository
    @Autowired
    private lateinit var friendRepository: FriendRepository

    @PostMapping("/api/login")
    fun login(
            @RequestBody
            loginRequest: LoginRequest
    ): Any {
        val user =
                userProfile.getUserProfileByUsername(loginRequest.username)
        if (user == null) {
            return CommonResponse("username not exist", 1)
        } else {
            if (!BCryptPasswordEncoder().matches(
                            loginRequest.password,
                            user.password
                    )) {
                return CommonResponse("password invalid", 1)

            }

        }
        return CommonResponse(
                LoginResponse(
                        Utils.getJWTFromInfoUser(
                                user.id,
                                user.username,
                                user.name
                        )
                )
        )
    }

    @PostMapping("/api/register")
    fun requester(
            @RequestBody user: UserProfile
    ): Any {
        var userDB = userProfile.getUserProfileByUsername(
                user.username
        )
        if (userDB != null) {
            return CommonResponse("User existed", 1)
        }
        user.password = BCryptPasswordEncoder()
                .encode(user.password)
        var u = userProfile.save(user)
        return CommonResponse(u)

    }

    @GetMapping("/api/getAllUserNotFriend")
    fun getAllUserNotFriend(
            @RequestHeader("Authorization") token: String
    ): Any {
        val token_ = token.substring("Bearer ".length)
        return CommonResponse(
                notFriendResponseRepository.getNotFriends(
                        Utils.getIdFromToken(token_)
                )
        )
    }

    @GetMapping("/api/getAllUserFriend")
    fun getAllUserFriend(
            @RequestHeader("Authorization") token: String
    ): Any {
        val token_ = token.substring("Bearer ".length)
        return CommonResponse(
                notFriendResponseRepository.getFriends(
                        Utils.getIdFromToken(token_)
                )
        )
    }

    @PostMapping("/api/addFriend")
    fun addFriend(
            @RequestHeader("Authorization") token: String,
            @RequestParam(value = "userId") friendId:Int
    ):Any{
        val token_ = token.substring("Bearer ".length)
        val userId = Utils.getIdFromToken(token_)
        val friends = friendRepository.getFriend(userId, friendId)
        if (friends.size > 0 ){
            return CommonResponse("Da tro thanh ban be", 1)
        }
        val friend = Friend()
        friend.senderId = userId
        friend.receiverId = friendId
        friendRepository.save(friend)
        return CommonResponse(friend)
    }

}