package gmao_backend.service;

import gmao_backend.dto.LoginRequest;
import gmao_backend.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

}
