package id.godohembon.themoviedb.utils

fun getImagePath(imgName: String?): String {
    return "https://image.tmdb.org/t/p/w500/${imgName}"
}