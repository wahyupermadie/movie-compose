package id.godohembon.themoviedb.utils.network

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
}