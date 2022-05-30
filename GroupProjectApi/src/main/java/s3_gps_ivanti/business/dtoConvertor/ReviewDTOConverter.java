package s3_gps_ivanti.business.dtoConvertor;

import s3_gps_ivanti.dto.review.CreateReviewRequestDTO;
import s3_gps_ivanti.dto.review.ReviewDTO;
import s3_gps_ivanti.repository.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDTOConverter {
    private ReviewDTOConverter(){}

    public static List<ReviewDTO> convertToListOfDTO(List<Review> reviews) {
        List<ReviewDTO> result = new ArrayList<>();

        for (Review r:reviews) {
            result.add(ReviewDTOConverter.convertToDTO(r));
        }

        return result;
    }

    private static ReviewDTO convertToDTO(Review r) {
        return ReviewDTO.builder()
                .customer(CustomerDTOConverter.convertToSmallDetailDTO(r.getCustomer()))
                .rating(r.getRating())
                .title(r.getTitle())
                .description(r.getDescription())
                .reply(ReplyDTOConverter.convertToDTO(r.getReply()))
                .build();
    }

    public static Review convertToEntityCreate(CreateReviewRequestDTO review) {
        return Review.builder()
                .applicationId(review.getApplicationID())
                .rating(review.getRating())
                .title(review.getTitle())
                .description(review.getDescription())
                .reply(null)
                .build();
    }
}
