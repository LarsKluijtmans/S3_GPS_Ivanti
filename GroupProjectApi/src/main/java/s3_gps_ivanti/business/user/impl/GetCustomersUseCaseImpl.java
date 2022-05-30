package s3_gps_ivanti.business.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.dtoConvertor.CustomerDTOConverter;
import s3_gps_ivanti.business.user.GetCustomersUseCase;
import s3_gps_ivanti.dto.user.CustomerBasicInfoDTO;
import s3_gps_ivanti.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class GetCustomersUseCaseImpl implements GetCustomersUseCase {

    private final UserRepository userRepository;

    @Override
    public List<CustomerBasicInfoDTO> getAllCustomers() {
        //TODO check if is admin
        return CustomerDTOConverter.convertToListDTO(userRepository.findAll());
    }
}