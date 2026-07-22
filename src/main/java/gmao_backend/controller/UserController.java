package gmao_backend.controller;

import gmao_backend.dto.CreateTechnicianRequest;
import gmao_backend.dto.UserResponse;
import gmao_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-technician")
    public ResponseEntity<UserResponse> createTechnician(@Valid @RequestBody CreateTechnicianRequest request) {
        UserResponse response = userService.createTechnician(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/technicians")
    public ResponseEntity<List<UserResponse>> getTechnicians() {
        List<UserResponse> technicians = userService.getTechnicians();
        return ResponseEntity.ok(technicians);
    }
}
