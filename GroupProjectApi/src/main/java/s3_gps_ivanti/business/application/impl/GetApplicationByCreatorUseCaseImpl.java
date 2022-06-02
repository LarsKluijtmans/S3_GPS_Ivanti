package s3_gps_ivanti.business.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.application.GetApplicationByCreatorUseCase;
import s3_gps_ivanti.business.dtoconvertor.ApplicationDTOConverter;
import s3_gps_ivanti.business.exception.CreatorNotFoundException;
import s3_gps_ivanti.dto.application.ApplicationBasicInfoDTO;
import s3_gps_ivanti.dto.creator.CreatorApplicationListDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.UserRepository;
import s3_gps_ivanti.repository.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class GetApplicationByCreatorUseCaseImpl implements GetApplicationByCreatorUseCase {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    @Override
    public CreatorApplicationListDTO getApplicationsByCreator(String username) {
        User creator = userRepository.findUserByUsername(username);
        if (creator == null){
            throw new CreatorNotFoundException();
        }

        List<ApplicationBasicInfoDTO> creatorApplications = creator.getApplications()
                .stream()
                .map(ApplicationDTOConverter::convertToDTO)
                .toList();
        return CreatorApplicationListDTO.builder()
                .myApplications(creatorApplications)
                .build();
//        List<ApplicationBasicInfoDTO> applications = applicationRepository.findAll()
//                .stream()
//                .filter(application -> application.getCreator() == creator)
//                .map(ApplicationDTOConverter::convertToDTO)
//                .toList();
//
//        return CreatorApplicationListDTO.builder()
//                .myApplications(applications)
//                .build();
    }
}
