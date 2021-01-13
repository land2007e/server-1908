package com.t3h.demoserver.model.response

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class NotFriendResponse {
    @Id
    var id = 0
    var username = ""
    var name = ""
    var avatar = ""
}