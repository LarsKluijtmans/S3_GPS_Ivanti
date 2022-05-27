package s3_gps_ivanti.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequestDTO {
    private String id;
    private String customer;
    private String applicationID;
    private int rating;
    private String title;
    private String description;
}
