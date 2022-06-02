package s3_gps_ivanti.dto.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder

public class GetAllApplicationsResponseDTO {
    private List<ApplicationBasicInfoDTO> applications;
}
