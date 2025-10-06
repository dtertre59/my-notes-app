package com.example.mynotesapp.presentation

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val colorHex: Long,
)