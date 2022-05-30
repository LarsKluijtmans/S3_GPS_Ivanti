package s3_gps_ivanti.business.version.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.exception.ApplicationNotFoundException;
import s3_gps_ivanti.business.version.DeleteVersionUseCase;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.entity.Application;
import s3_gps_ivanti.repository.entity.Version;

import javax.transaction.Transactional;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class DeleteVersionUseCaseImpl implements DeleteVersionUseCase {

    private final ApplicationRepository applicationRepository;

    @Override
    public void deleteVersion(String applicationID, double number) {

        //TODO check token.userid is creator that made this app or admin
        Application application = applicationRepository.findById(applicationID).orElse(null);

        if(application == null) {
            throw new ApplicationNotFoundException();
        }

        for (Version v: application.getVersions()) {
            if(v.getNumber() == number){
                application.getVersions().remove(v);
            }
            break;
        }

        applicationRepository.save(application);
    }
}
