package com.epicwindmill.decomposekmmnavigationsample

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}