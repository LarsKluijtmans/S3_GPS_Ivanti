package s3_gps_ivanti.business.application.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.application.GetApplicationsBasicInfoUseCase;
import s3_gps_ivanti.business.dtoconvertor.ApplicationDTOConverter;
import s3_gps_ivanti.dto.application.ApplicationBasicInfoDTO;
import s3_gps_ivanti.dto.application.GetAllApplicationsRequestDTO;
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

    @Override
    public GetAllApplicationsResponseDTO getAllApplications(GetAllApplicationsRequestDTO request) {
        List<Application> results;

        if (StringUtils.hasText(request.getName())) {
            if (BooleanUtils.isNotFalse(request.getOrderByName())) {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = applicationRepository.findAllByNameContainingIgnoreCaseAndStatusOrderByNameAsc(request.getName(), request.getStatus());
                } else {
                    results = applicationRepository.findAllByNameContainingIgnoreCaseAndStatusOrderByNameDesc(request.getName(), request.getStatus());
                }
            } else {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = applicationRepository.findAllByNameContainingIgnoreCaseAndStatusOrderByRatingAsc(request.getName(), request.getStatus());
                } else {
                    results = applicationRepository.findAllByNameContainingIgnoreCaseAndStatusOrderByRatingDesc(request.getName(), request.getStatus());
                }
            }
        } else {
            if (BooleanUtils.isNotFalse(request.getOrderByName())) {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = applicationRepository.findAllByStatusOrderByNameAsc(request.getStatus());
                } else {
                    results = applicationRepository.findAllByStatusOrderByNameDesc(request.getStatus());
                }
            } else {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = applicationRepository.findAllByStatusOrderByRatingAsc(request.getStatus());
                } else {
                    results = applicationRepository.findAllByStatusOrderByRatingDesc(request.getStatus());
                }
            }
        }

        final GetAllApplicationsResponseDTO response = new GetAllApplicationsResponseDTO();
        List<ApplicationBasicInfoDTO> applicationsDTO = results
                .stream()
                .map(ApplicationDTOConverter::convertToDTO)
                .toList();
        response.setApplications(applicationsDTO);
        return response;
    }
}
