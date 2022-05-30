package s3_gps_ivanti.business.review.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.dtoConvertor.ReplyDTOConverter;
import s3_gps_ivanti.business.review.UpdateReviewUseCase;
import s3_gps_ivanti.business.validation.ReviewIDValidation;
import s3_gps_ivanti.dto.review.CreateUpdateDeleteReplyDTO;
import s3_gps_ivanti.dto.review.UpdateReviewRequestDTO;
import s3_gps_ivanti.repository.ReviewRepository;
import s3_gps_ivanti.repository.entity.Review;

@Service
@Primary
@RequiredArgsConstructor
public class UpdateReviewUseCaseImpl implements UpdateReviewUseCase {
    private final ReviewRepository reviewRepository;
    private final ReviewIDValidation idValidCheck;

    @Override
    public void updateReview(UpdateReviewRequestDTO request) {
        idValidCheck.reviewInvalid(request.getId());

        Review review = reviewRepository.findById(request.getId()).orElse(null);
        review.setDescription(request.getDescription());
        review.setTitle(request.getTitle());
        review.setRating(request.getRating());

        reviewRepository.save(review);
    }
    @Override
    public void replyAction(CreateUpdateDeleteReplyDTO request) {
        idValidCheck.reviewInvalid(request.getId());

        Review review = reviewRepository.findById(request.getId()).orElse(null);
        review.setReply(ReplyDTOConverter.convertToEntity(request.getReply()));

        reviewRepository.save(review);
    }
}
