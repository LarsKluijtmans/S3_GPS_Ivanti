package s3_gps_ivanti.controller;


import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3_gps_ivanti.business.review.CreateReviewUseCase;
import s3_gps_ivanti.business.review.DeleteReviewUseCase;
import s3_gps_ivanti.business.review.UpdateReviewUseCase;
import s3_gps_ivanti.configuration.security.isauthenticated.IsAuthenticated;
import s3_gps_ivanti.dto.review.CreateReviewRequestDTO;
import s3_gps_ivanti.dto.review.CreateReviewResponseDTO;
import s3_gps_ivanti.dto.review.UpdateReviewRequestDTO;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class ReviewController {

   private final CreateReviewUseCase createReviewService;
   private final DeleteReviewUseCase deleteReviewService;
   private final UpdateReviewUseCase updateReviewService;

    @IsAuthenticated
    @RolesAllowed({"ROLE_Customer"})
    @PostMapping()
    public ResponseEntity<CreateReviewResponseDTO>  createReview(@RequestBody CreateReviewRequestDTO review) {
        CreateReviewResponseDTO response = createReviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_Customer"})
    @PutMapping()
    public ResponseEntity<Object>  updateReview(@RequestBody UpdateReviewRequestDTO review) {
        updateReviewService.updateReview(review);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_Customer"})
    @DeleteMapping("/{reviewID}")
    public ResponseEntity<Object>  deleteReview(@PathVariable("reviewID") String reviewID) {
        deleteReviewService.deleteReview(reviewID);
        return ResponseEntity.ok().build();
    }
}
