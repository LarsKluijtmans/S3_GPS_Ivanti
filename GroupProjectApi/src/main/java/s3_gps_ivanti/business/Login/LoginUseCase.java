package s3_gps_ivanti.business.login;


import s3_gps_ivanti.dto.login.LoginRequestDTO;
import s3_gps_ivanti.dto.login.LoginResponseDTO;
import s3_gps_ivanti.repository.entity.User;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
    String generateAccessToken(User user);
}
