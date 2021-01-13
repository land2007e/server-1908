package com.t3h.demoserver.model

class CommonResponse {
    var code = 0
    var message = ""
    var data: Any? = null

    constructor(data: Any) {
        code = 0
        this.data = data
        this.message = ""
    }

    constructor(message: String, code: Int) {
        this.code = code
        this.data = null
        this.message = message
    }
    constructor(message: String, code: Int, data:Any) {
        this.code = code
        this.data = data
        this.message = message
    }
}