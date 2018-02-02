package com.acv.marvel.domain

sealed class DomainError(val error: String = "Error")
object NotInternetDomainError : DomainError()
data class UnknownDomainError(val errorMessage: String = "Unknown Error") : DomainError(errorMessage)
