package com.example.interviewtask.data.model


data class MainResult(
    val page: String,
    val data: List<DataModel>,
    val total: Int,
    val total_results: Int
)
