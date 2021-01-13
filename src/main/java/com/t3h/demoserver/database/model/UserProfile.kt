package com.t3h.demoserver.database.model

import javax.persistence.*

@Entity(name = "user_profile")
class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    var username=""
    var password=""
    var name=""
    var avatar=""
}