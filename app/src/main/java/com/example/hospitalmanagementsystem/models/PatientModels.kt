package com.example.hospitalmanagementsystem.models

import androidx.core.app.GrammaticalInflectionManagerCompat

data class PatientModels(
    var id: String? = null,
    val name: String? = null,
    val age: String? = null,
    val phone: String? = null,
    val illness: String? = null,
    val gender: String? = null,
    val date_of_visit:String? = null,
    val imageUrl: String? = null
)