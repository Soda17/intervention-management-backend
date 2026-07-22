package gmao_backend.service.impl;

import gmao_backend.dto.CreateTechnicianRequest;
import gmao_backend.dto.UserResponse;
import gmao_backend.entity.Role;
import gmao_backend.entity.User;
import gmao_backend.exception.BadRequestException;
import gmao_backend.mapper.UserMapper;
import gmao_backend.repository.UserRepository;
import gmao_backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse createTechnician(CreateTechnicianRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Un utilisateur avec cet email existe déjà");
        }

        User technician = User.builder()
                .firstname(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.TECHNICIAN)
                .build();

        User savedTechnician = userRepository.save(technician);
        return userMapper.toResponse(savedTechnician);
    }

    @Override
    @Transactional
    public List<UserResponse> getTechnicians() {
        return userMapper.toResponseList(userRepository.findByRole(Role.TECHNICIAN));
    }

}
