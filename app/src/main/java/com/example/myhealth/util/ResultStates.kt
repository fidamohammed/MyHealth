package com.example.myhealth.util

sealed class ResultStates{
    object Loading: ResultStates()
    class Success<T>(val data: T?): ResultStates()
    class Error(val error: String): ResultStates()

}
