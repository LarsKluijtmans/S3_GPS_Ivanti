package s3_gps_ivanti.business.dtoconvertor;

import org.apache.commons.lang3.RandomStringUtils;
import s3_gps_ivanti.dto.application.*;
import s3_gps_ivanti.dto.review.CreateReviewRequestDTO;
import s3_gps_ivanti.dto.user.CustomerSmallDetailDTO;
import s3_gps_ivanti.repository.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationDTOConverter {

    public static CreateApplicationResponseDTO convertToDTOCreateResponse(Application application) {
        return CreateApplicationResponseDTO.builder()
                .id(application.getId())
                .build();
    }

    public static Application convertToEntity(CreateApplicationRequestDTO application) {
        return Application.builder()
                .id(RandomStringUtils.randomAlphanumeric(25))
                .name(application.getName())
                .description(application.getDescription())
                .icon(application.getIcon())
                .screenshots(application.getScreenshots())
                .status(false)
                .versions(List.of(new Version(1.0,0,application.getApplicationLocation(),Collections.emptyList())))
                .rating(new RatingAnalytics(0,0,0,0,0))
                .build();
    }

    public static List<ApplicationBasicInfoDTO> convertListToDTO(List<Application> applications) {

        List<ApplicationBasicInfoDTO> result = new ArrayList<>();

        for (Application app: applications) {
            result.add(ApplicationDTOConverter.convertToDTO(app));
        }

        return result;
    }

    public static ApplicationBasicInfoDTO convertToDTO(Application app) {
        return ApplicationBasicInfoDTO.builder()
                .name(app.getName())
                .icon(app.getIcon())
                .build();
    }

    public static ApplicationDetailedInfoDTO convertToApplicationDetailedInfo(Application application) {

        int totalDownloads = 0;
        for (Version v : application.getVersions()) {
            totalDownloads += v.getTotalDownloads();
        }

        //TODO Mohammad make this

        int avgRating = 0;


        return ApplicationDetailedInfoDTO.builder()
                .name(application.getName())
                .icon(application.getIcon())
                .description(application.getDescription())
                .screenshots(application.getScreenshots())
                .totalDownloads(totalDownloads)
                .avgRating(avgRating)
                .versions(VersionDTOConverter.convertToListOfDTO(application.getVersions()))
                .build();
    }

    public static Application convertToEntityForUpdate(UpdateApplicationRequestDTO app, Application application) {
        return Application.builder()
                .id(application.getId())
                .name(application.getName())
                .description(app.getDescription())
                .icon(app.getIcon())
                .screenshots(app.getScreenshots())
                .status(application.isStatus()?
                        true
                        :false)
                .versions(application.getVersions())
                .rating(application.getRating())
                .build();
    }
}
