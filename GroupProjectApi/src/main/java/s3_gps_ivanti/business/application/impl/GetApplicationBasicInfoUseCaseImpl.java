package s3_gps_ivanti.business.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.application.GetApplicationsBasicInfoUseCase;
import s3_gps_ivanti.business.dtoconvertor.ApplicationDTOConverter;
import s3_gps_ivanti.dto.application.ApplicationBasicInfoDTO;
import s3_gps_ivanti.dto.application.GetAllApplicationsResponseDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.entity.Application;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class GetApplicationBasicInfoUseCaseImpl implements GetApplicationsBasicInfoUseCase {
    private final ApplicationRepository applicationRepository;

//    @Override
//    public List<ApplicationBasicInfoDTO> getApplications() {
//        return ApplicationDTOConverter.convertListToDTO(applicationRepository.findAll());
//    }

    @Override
    public GetAllApplicationsResponseDTO getAllApplications() {
        List<ApplicationBasicInfoDTO> applications = applicationRepository.findAll()
                .stream()
                .filter(application -> !application.isStatus())
                .map(ApplicationDTOConverter::convertToDTO)
                .toList();

        return GetAllApplicationsResponseDTO.builder()
                .applications(applications)
                .build();
    }
}
