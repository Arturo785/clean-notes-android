package com.codingwithmitch.cleannotes.business.data.cache


// a wrapper of what gets retrieved by the cache
sealed class CacheResult<out T> {

    data class Success<out T>(val value: T): CacheResult<T>()

    data class GenericError(
        val errorMessage: String? = null
    ): CacheResult<Nothing>()
}
