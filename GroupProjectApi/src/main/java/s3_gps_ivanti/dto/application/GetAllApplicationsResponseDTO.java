package s3_gps_ivanti.dto.application;

import lombok.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllApplicationsResponseDTO {
    private List<ApplicationBasicInfoDTO> applications;
}
