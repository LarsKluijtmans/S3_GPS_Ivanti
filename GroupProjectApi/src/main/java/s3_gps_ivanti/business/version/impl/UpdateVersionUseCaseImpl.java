package s3_gps_ivanti.business.version.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.dtoConvertor.VersionDTOConverter;
import s3_gps_ivanti.business.exception.ApplicationNotFoundException;
import s3_gps_ivanti.business.version.UpdateVersionUseCase;
import s3_gps_ivanti.dto.version.UpdateVersionRequestDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.entity.Application;
import s3_gps_ivanti.repository.entity.Version;

import javax.transaction.Transactional;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class UpdateVersionUseCaseImpl implements UpdateVersionUseCase {

    private final ApplicationRepository applicationRepository;


    @Override
    public void updateVersion(UpdateVersionRequestDTO versionRequestDTO) {

        //TODO check token.userid is creator that made this app

        Application application = applicationRepository.findById(versionRequestDTO.getApplicationID()).orElse(null);

        if(application == null) {
            throw new ApplicationNotFoundException();
        }

        for (Version v: application.getVersions()) {
            if(v.getNumber() == versionRequestDTO.getNumber()){
                v = VersionDTOConverter.convertToEntityForUpdate(versionRequestDTO,v);
            }
            break;
        }

        applicationRepository.save(application);
    }
}
