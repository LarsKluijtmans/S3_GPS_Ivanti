package s3_gps_ivanti.business.login.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import s3_gps_ivanti.business.exception.InvalidCredentialsException;
import s3_gps_ivanti.business.login.LoginUseCase;
import s3_gps_ivanti.dto.login.LoginRequestDTO;
import s3_gps_ivanti.dto.login.LoginResponseDTO;
import s3_gps_ivanti.repository.UserRepository;
import s3_gps_ivanti.repository.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository repository;

    @InjectMocks
    private LoginUseCase service;


    void loginValid(){
        User savedCustomer = User.builder()
                .id("1l")
                .username("Red")
                .email("red123@gmail.com")
                .password("123Red")
                .permission("CUSTOMER")
                .build();

        savedCustomer.setRoles(List.of("CUSTOMER"));

        when(repository.existsByUsername("Red")).thenReturn(true);
        when(repository.findUserByUsername("red123@gmail.com")).thenReturn(savedCustomer);


        LoginRequestDTO request = LoginRequestDTO.builder()
                .username("Red")
                .password("123Red")
                .build();

        when(service.generateAccessToken(savedCustomer)).thenReturn("h123H123s");
        when(passwordEncoder.matches(request.getPassword(), savedCustomer.getPassword())).thenReturn(true);

        LoginResponseDTO expectedDTO = LoginResponseDTO.builder()
                .accessToken("h123H123s")
                .build();

        LoginResponseDTO actualDTO = service.login(request);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).existsByUsername("Red");
        verify(repository).findUserByUsername("Red");
    }


    void loginInvalidCredential(){
        User savedCustomer = User.builder()
                .id("1l")
                .username("Red")
                .email("red123@gmail.com")
                .password("123Red")
                .permission("CUSTOMER")
                .build();

        savedCustomer.setRoles(List.of("CUSTOMER"));

        when(repository.existsByUsername("Red")).thenReturn(true);
        when(repository.findUserByUsername("red123@gmail.com")).thenReturn(savedCustomer);


        LoginRequestDTO request = LoginRequestDTO.builder()
                .username("Red")
                .password("123Red")
                .build();
        when(passwordEncoder.matches(request.getPassword(), savedCustomer.getPassword())).thenReturn(false);

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, ()->service.login(request));

        assertEquals("INVALID_CREDENTIALS", exception.getReason());

        verify(repository).existsByUsername("Red");
        verify(repository).findUserByUsername("Red");
    }
}