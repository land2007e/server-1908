package com.t3h.demoserver.database.model

import java.sql.Timestamp
import javax.persistence.*

@Entity
open class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id =0
    @Column(name = "sender_id")
    var senderId = 0
    @Column(name = "receiver_id")
    var receiverId = 0
    @Column(name = "created_time")
    var createdTime:Timestamp?=null
}