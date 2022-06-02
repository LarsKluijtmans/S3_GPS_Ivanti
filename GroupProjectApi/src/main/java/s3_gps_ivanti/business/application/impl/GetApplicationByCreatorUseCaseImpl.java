package s3_gps_ivanti.business.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.application.GetApplicationByCreatorUseCase;
import s3_gps_ivanti.dto.creator.CreatorApplicationListDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.UserRepository;
import s3_gps_ivanti.repository.entity.User;

import javax.transaction.Transactional;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class GetApplicationByCreatorUseCaseImpl implements GetApplicationByCreatorUseCase {

    private final UserRepository userRepository;

    @Override
    public CreatorApplicationListDTO getApplicationsByCreator(String username) {
        User creator = userRepository.findUserByUsername(username);
        return null;
    }
}
