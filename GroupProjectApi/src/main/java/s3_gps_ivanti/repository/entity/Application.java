package s3_gps_ivanti.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Application")
public class Application {

    @Id
    private String id;
    private String creatorID;
    private String name;
    private String description;
    private String icon;
    private List<String> screenshots;
    private boolean status;
    private List<Version> versions;
    private List<Review> reviews;
    private RatingAnalytics rating;

    public Application(String id) {
        this.id = id;
    }
}
