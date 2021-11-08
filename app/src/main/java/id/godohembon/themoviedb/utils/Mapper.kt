package id.godohembon.themoviedb.utils

interface Mapper<I, O> {
    fun map(input: I): O
}