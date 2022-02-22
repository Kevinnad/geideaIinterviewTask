package com.example.interviewtask.network

import java.lang.Exception

sealed class ResultWrapper<out T>{
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Failure(val value: String): ResultWrapper<String>()
    data class GenericError(val code: Int? = null, val value: String, val exception: Exception): ResultWrapper<Nothing>()
    data class NetworkError(val value: String, val exception: Exception): ResultWrapper<Nothing>()
}