package com.example.promosee.model.local.preference

data class CompanyModel (
    val username: String,
    val email: String,
    val password: String,
    val company_name: String,
    val categories: List<String>,
)