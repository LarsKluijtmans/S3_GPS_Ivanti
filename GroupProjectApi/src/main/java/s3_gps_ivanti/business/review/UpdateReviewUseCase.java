package s3_gps_ivanti.business.review;

import s3_gps_ivanti.dto.review.CreateUpdateDeleteReplyDTO;
import s3_gps_ivanti.dto.review.UpdateReviewRequestDTO;

public interface UpdateReviewUseCase {
    void updateReview(UpdateReviewRequestDTO review);
    void replyAction(CreateUpdateDeleteReplyDTO request);
}
