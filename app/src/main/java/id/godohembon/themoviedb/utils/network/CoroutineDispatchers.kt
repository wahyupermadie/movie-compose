package id.godohembon.themoviedb.utils.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatchers {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
}