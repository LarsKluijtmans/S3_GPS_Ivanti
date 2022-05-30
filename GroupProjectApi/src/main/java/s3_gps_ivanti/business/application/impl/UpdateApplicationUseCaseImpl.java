package s3_gps_ivanti.business.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.application.UpdateApplicationUseCase;
import s3_gps_ivanti.business.dtoConvertor.ApplicationDTOConverter;
import s3_gps_ivanti.business.exception.ApplicationNotFoundException;
import s3_gps_ivanti.dto.application.UpdateApplicationRequestDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.entity.Application;

import javax.transaction.Transactional;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class UpdateApplicationUseCaseImpl implements UpdateApplicationUseCase {

    private final ApplicationRepository applicationRepository;

    @Override
    public void updateApplications(UpdateApplicationRequestDTO applicationRequestDTO) {

        //TODO check token.userid
        Application application = applicationRepository.findById(applicationRequestDTO.getId()).orElse(null);

        if(application == null) {
            throw new ApplicationNotFoundException();
        }

        Application newApplication = ApplicationDTOConverter.convertToEntityForUpdate(applicationRequestDTO, application);

        applicationRepository.save(newApplication);
    }
}
