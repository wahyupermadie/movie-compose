package id.godohembon.themoviedb.domain.mapper

import id.godohembon.themoviedb.data.dto.review.ReviewResponseDto
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.utils.Mapper

class ReviewMapper : Mapper<ReviewResponseDto, List<Review>?> {
    override fun map(input: ReviewResponseDto): List<Review>? {
        return input.results?.map {
            Review(
                author = it.author ?: "",
                id = it.id ?: "",
                createdAt = it.createdAt ?: "",
                content = it.content ?: ""
            )
        }
    }
}