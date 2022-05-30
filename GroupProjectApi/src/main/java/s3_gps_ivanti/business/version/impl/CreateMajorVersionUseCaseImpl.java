package s3_gps_ivanti.business.version.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.dtoConvertor.VersionDTOConverter;
import s3_gps_ivanti.business.exception.ApplicationNotFoundException;
import s3_gps_ivanti.business.exception.VersionNumberIncorrectException;
import s3_gps_ivanti.business.version.CreateMajorVersionUseCase;
import s3_gps_ivanti.business.version.GetLatestVersionUseCase;
import s3_gps_ivanti.dto.version.CreateMajorVersionRequestDTO;
import s3_gps_ivanti.dto.version.CreateMajorVersionResponseDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.entity.Application;
import s3_gps_ivanti.repository.entity.Version;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class CreateMajorVersionUseCaseImpl implements CreateMajorVersionUseCase {

    private final ApplicationRepository applicationRepository;
    private final GetLatestVersionUseCase getLatestVersion;

    @Override
    public CreateMajorVersionResponseDTO createVersion(CreateMajorVersionRequestDTO versionRequestDTO) {
        //TODO check token.userid is creator that made this app

        Application application = applicationRepository.findById(versionRequestDTO.getApplicationID()).orElse(null);

        if(application == null) {
            throw new ApplicationNotFoundException();
        }

        Version latestVersion = getLatestVersion.getLatestVersion(application);

        if(latestVersion.getNumber() != versionRequestDTO.getNumber() + 1.0){
            throw new VersionNumberIncorrectException();
        }

        List<Version> newVersions = application.getVersions();

        Version version = VersionDTOConverter.convertToEntityForCreate(versionRequestDTO);

        newVersions.add(version);

        application.setVersions(newVersions);

        applicationRepository.save(application);

        return VersionDTOConverter.convertToDTOForMajorResponse(version);
    }
}
