package com.example.hospitalmanagementsystem.models

data class UserModel(
    val username: String = "",
    val email: String = "",
    val phoneNumber: String = "",  // ✅ added
    val userId: String = ""        // ✅ duplicate removed
)