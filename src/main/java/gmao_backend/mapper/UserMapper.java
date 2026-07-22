package gmao_backend.mapper;

import gmao_backend.dto.LoginResponse;
import gmao_backend.dto.UserResponse;
import gmao_backend.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstname())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public LoginResponse toLoginResponse(User user) {
        if (user == null) {
            return null;
        }
        return LoginResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstname())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public List<UserResponse> toResponseList(List<User> users) {
        if (users == null) {
            return Collections.emptyList();
        }
        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
