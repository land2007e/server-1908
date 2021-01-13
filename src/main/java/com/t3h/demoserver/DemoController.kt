package com.t3h.demoserver

import org.apache.commons.io.IOUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
open class DemoController {

    @GetMapping("/api/getDemo")
    fun getDemo():Any{
        return "Hello java spring boot rest api"
    }

    @GetMapping("/api/getImage",
            produces = arrayOf(MediaType.IMAGE_JPEG_VALUE))
    fun getImage(
            @RequestParam(value = "imageName", required = true) name:String
    ):Any{
        val input = ClassPathResource(name).inputStream
        val b = IOUtils.toByteArray(input)
        input.close()
        return b
    }



}