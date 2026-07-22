package gmao_backend.controller;

import gmao_backend.dto.CloseInterventionRequest;
import gmao_backend.dto.CreateInterventionRequest;
import gmao_backend.dto.InterventionResponse;
import gmao_backend.dto.UpdateInterventionRequest;
import gmao_backend.entity.Status;
import gmao_backend.service.InterventionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interventions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InterventionController {
    private final InterventionService interventionService;

    @PostMapping("/create")
    public ResponseEntity<InterventionResponse> create(@Valid @RequestBody CreateInterventionRequest request) {
        InterventionResponse response = interventionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<InterventionResponse>> getAll() {
        List<InterventionResponse> interventions = interventionService.getAll();
        return ResponseEntity.ok(interventions);
    }

    @GetMapping("/technician/{id}")
    public ResponseEntity<List<InterventionResponse>> getByTechnician(@PathVariable Long id) {
        List<InterventionResponse> interventions = interventionService.getByTechnician(id);
        return ResponseEntity.ok(interventions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterventionResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInterventionRequest request) {
        InterventionResponse response = interventionService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<InterventionResponse> changeStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
        InterventionResponse response = interventionService.changeStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<InterventionResponse> closeIntervention(
            @PathVariable Long id,
            @Valid @RequestBody CloseInterventionRequest request) {
        InterventionResponse response = interventionService.closeIntervention(id, request);
        return ResponseEntity.ok(response);
    }
}
