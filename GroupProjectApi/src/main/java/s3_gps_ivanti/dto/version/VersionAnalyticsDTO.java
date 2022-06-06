package s3_gps_ivanti.dto.version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s3_gps_ivanti.repository.entity.DownloadsPerMonth;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VersionAnalyticsDTO {
    private double number;
    private List<DownloadsPerMonth> downloads;
    private long totalDownloads;
}
