package com.example.mynotesapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform