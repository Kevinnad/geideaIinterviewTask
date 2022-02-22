package com.example.interviewtask.network

import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

private const val genericErrorMsg = "Something went wrong try again later!"
private const val networkErrorMsg = "Check your internet connection!"

/*Network Request Handler - Handles Success and other Exceptions*/
suspend fun <T: Any> handleRequest(requestFunc: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(requestFunc.invoke())
    } catch (throwable: Throwable) {
        Log.e("Error",throwable.message?:"")
        when (throwable) {
            is UnknownHostException -> ResultWrapper.NetworkError(networkErrorMsg,throwable)
            is IOException -> ResultWrapper.NetworkError(networkErrorMsg,throwable)
            is HttpException -> {
                val code = throwable.code()
                ResultWrapper.GenericError(code, genericErrorMsg,throwable)
            }
            else -> {
                ResultWrapper.GenericError(null, genericErrorMsg,throwable as Exception)
            }
        }
    }
}