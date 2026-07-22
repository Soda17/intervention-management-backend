package gmao_backend.dto;

import gmao_backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
