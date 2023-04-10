package com.github.bkmbigo.basicapplication.utils

sealed class Result<out R> {
    data class Success<out R>(val data: R) : Result<R>() {
        override fun toString(): String {
            return "Success [data = $data]"
        }
    }

    data class Error(val exception: Exception) : Result<Nothing>() {
        override fun toString(): String {
            return "Error[exception = $exception]"
        }
    }

    object Loading : Result<Nothing>() {
        override fun toString(): String {
            return "Loading"
        }
    }

    val succeeded
        get() = this is Success && data != null
}
