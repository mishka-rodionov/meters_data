package com.rodionov.base.state

class ErrorHandler {
    fun proceedException(exception: Throwable): Failure {
        return Failure.CommonError
    }
}