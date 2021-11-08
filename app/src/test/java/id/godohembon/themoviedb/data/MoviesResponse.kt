package id.godohembon.themoviedb.data

import id.godohembon.themoviedb.domain.model.MovieModel
import kotlin.random.Random

object MoviesResponse {
    fun getMovies(): List<MovieModel> {
        return listOf(
            MovieModel(12, "Wahyu Movie", "wahyu.png", "wahyu.png"),
            MovieModel(Random.nextInt(), Random.toString(), Random.toString(), Random.toString()),
            MovieModel(Random.nextInt(), Random.toString(), Random.toString(), Random.toString()),
            MovieModel(Random.nextInt(), Random.toString(), Random.toString(), Random.toString()),
            MovieModel(Random.nextInt(), Random.toString(), Random.toString(), Random.toString())
        )
    }
}