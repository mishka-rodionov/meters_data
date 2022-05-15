package com.rodionov.base.state

sealed class Failure {

    object NetworkConnection : Failure()
    object ServerError : Failure()
    object CommonError : Failure()
    object AuthError : Failure()
}
