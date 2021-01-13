package com.t3h.demoserver

import org.springframework.boot.SpringApplication

object Tes {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(Tes::class.java, *args)
    }
}