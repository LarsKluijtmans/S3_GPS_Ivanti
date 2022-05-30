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

import javax.transaction.Transactional;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final UserRepository userRepository;

    @Override
    public void updateCustomer(UpdateCustomerRequestDTO customerRequestDTO) {

        //TODO check token.userid
        if(userRepository.findUserByUsername(customerRequestDTO.getUsername()) != null
                && !(userRepository.findUserByUsername(customerRequestDTO.getUsername()).getId().equals(customerRequestDTO.getId()))) {
            throw new UsernameAlreadyExistsException();
        }

        if(userRepository.findUserByEmail(customerRequestDTO.getEmail()) != null
                && !(userRepository.findUserByEmail(customerRequestDTO.getEmail()).getId().equals(customerRequestDTO.getId()))) {
            throw new EmailAlreadyExistsException();
        }

        User oldCustomer = userRepository.findById(customerRequestDTO.getId()).orElse(null);

        if(oldCustomer == null) {
            throw new CustomerNotFoundException();
        }

        if(oldCustomer.getPermission() != "Customer" || !(oldCustomer.getRoles().contains("Customer"))) {
            throw new CustomerNotFoundException();
        }

        User user = CustomerDTOConverter.convertToEntity(customerRequestDTO,oldCustomer);

        userRepository.save(user);
    }
}