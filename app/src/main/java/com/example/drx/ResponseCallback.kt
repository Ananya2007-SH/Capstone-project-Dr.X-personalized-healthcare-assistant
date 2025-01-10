package com.example.drx

interface ResponseCallback {

    fun onResponse(response: String)
    fun onError(throwable: Throwable)
}