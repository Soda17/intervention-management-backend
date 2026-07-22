package gmao_backend.service;

import gmao_backend.dto.CreateTechnicianRequest;
import gmao_backend.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createTechnician(CreateTechnicianRequest request);
    List<UserResponse> getTechnicians();
}
