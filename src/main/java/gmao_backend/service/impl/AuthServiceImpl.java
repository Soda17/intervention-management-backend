package gmao_backend.service.impl;

import gmao_backend.dto.LoginRequest;
import gmao_backend.dto.LoginResponse;
import gmao_backend.entity.User;
import gmao_backend.exception.UnauthorizedException;
import gmao_backend.mapper.UserMapper;
import gmao_backend.repository.UserRepository;
import gmao_backend.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Email ou mot de passe incorrect");
        }

        return userMapper.toLoginResponse(user);
    }
}
