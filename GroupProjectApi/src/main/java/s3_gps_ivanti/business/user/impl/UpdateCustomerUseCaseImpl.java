package s3_gps_ivanti.business.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import s3_gps_ivanti.business.dtoConvertor.CustomerDTOConverter;
import s3_gps_ivanti.business.exception.CustomerNotFoundException;
import s3_gps_ivanti.business.exception.EmailAlreadyExistsException;
import s3_gps_ivanti.business.exception.UsernameAlreadyExistsException;
import s3_gps_ivanti.business.user.UpdateCustomerUseCase;
import s3_gps_ivanti.dto.user.UpdateCustomerRequestDTO;
import s3_gps_ivanti.repository.UserRepository;
import s3_gps_ivanti.repository.entity.User;

@Service
@Primary
@RequiredArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final UserRepository userRepository;

    @Override
    public void updateCustomer(UpdateCustomerRequestDTO customerRequestDTO) {

        //TODO check token.userid
        if(userRepository.findUserByUsername(customerRequestDTO.getUsername()) != null) {
            throw new UsernameAlreadyExistsException();
        }

        if(userRepository.findUserByEmail(customerRequestDTO.getUsername()) != null) {
            throw new EmailAlreadyExistsException();
        }

        User oldCustomer = userRepository.findById(customerRequestDTO.getId()).orElse(null);

        if(oldCustomer == null) {
            throw new CustomerNotFoundException();
        }

        if(oldCustomer.getPermission() != "Customer" || !(oldCustomer.getRoles().contains("Customer"))) {
            throw new CustomerNotFoundException();
        }

        User user = CustomerDTOConverter.ConvertToEntity(customerRequestDTO,oldCustomer);

        userRepository.save(user);
    }
}