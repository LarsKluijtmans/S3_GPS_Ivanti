package s3_gps_ivanti.business.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.application.CreateApplicationUseCase;
import s3_gps_ivanti.business.dtoConvertor.ApplicationDTOConverter;
import s3_gps_ivanti.business.exception.ApplicationNameNotUnique;
import s3_gps_ivanti.dto.application.CreateApplicationRequestDTO;
import s3_gps_ivanti.dto.application.CreateApplicationResponseDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.entity.Application;

import javax.transaction.Transactional;


@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class CreateApplicationUseCaseImpl implements CreateApplicationUseCase {

    private final ApplicationRepository applicationRepository;

    @Override
    public CreateApplicationResponseDTO createApplications(CreateApplicationRequestDTO applicationRequestDTO) {

        if(applicationRepository.findByName(applicationRequestDTO.getName()) != null){
            throw new ApplicationNameNotUnique();
        }

        applicationRepository.save(ApplicationDTOConverter.convertToEntity(applicationRequestDTO));

        Application newApplication = applicationRepository.findByName(applicationRequestDTO.getName());

        return ApplicationDTOConverter.convertToDTOCreateResponse(newApplication);
    }
}
