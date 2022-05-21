package s3_gps_ivanti.business.dtoConvertor;

import org.apache.commons.lang3.RandomStringUtils;
import s3_gps_ivanti.dto.user.CreateCustomerRequestDTO;
import s3_gps_ivanti.dto.user.CreateCustomerResponseDTO;
import s3_gps_ivanti.dto.user.CustomerBasicInfoDTO;
import s3_gps_ivanti.dto.user.UpdateCustomerRequestDTO;
import s3_gps_ivanti.repository.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDTOConverter {

    public static User ConvertToEntity(CreateCustomerRequestDTO customerRequestDTO) {
        return User.builder()
                .id(RandomStringUtils.randomAlphabetic(23))
                .username(customerRequestDTO.getUsername())
                .email(customerRequestDTO.getEmail())
                .password(customerRequestDTO.getPassword())
                .roles(List.of("Customer"))
                .permission("Customer")
                .applicationID(Collections.emptyList())
                .build();
    }
    public static User ConvertToEntity(UpdateCustomerRequestDTO customerRequestDTO, User oldCustomer) {
        return User.builder()
                .id(customerRequestDTO.getId())
                .username(customerRequestDTO.getUsername())
                .email(customerRequestDTO.getEmail())
                .password(customerRequestDTO.getPassword())
                .roles(oldCustomer.getRoles())
                .permission(oldCustomer.getPermission())
                .applicationID(oldCustomer.getApplicationID())
                .build();
    }
    private static CustomerBasicInfoDTO ConvertToDTO(User user) {
        return CustomerBasicInfoDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
    public static List<CustomerBasicInfoDTO> ConvertToListDTO(List<User> all) {
        List<CustomerBasicInfoDTO> result = new ArrayList<>();

        for (User user: all) {
            result.add(CustomerDTOConverter.ConvertToDTO(user));
        }

        return result;
    }

    public static CreateCustomerResponseDTO ConvertToDTOCreateResponse(User user) {
        return CreateCustomerResponseDTO.builder()
                .username(user.getUsername())
                .build();
    }
}
