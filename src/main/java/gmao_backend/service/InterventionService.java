package gmao_backend.service;

import gmao_backend.dto.CloseInterventionRequest;
import gmao_backend.dto.CreateInterventionRequest;
import gmao_backend.dto.InterventionResponse;
import gmao_backend.dto.UpdateInterventionRequest;
import gmao_backend.entity.Status;

import java.util.List;

public interface InterventionService {
    InterventionResponse create(CreateInterventionRequest request);

    List<InterventionResponse> getAll();

    List<InterventionResponse> getByTechnician(Long technicianId);

    InterventionResponse update(Long id, UpdateInterventionRequest request);

    InterventionResponse changeStatus(Long id, Status status);

    InterventionResponse closeIntervention(Long id, CloseInterventionRequest request);
}
