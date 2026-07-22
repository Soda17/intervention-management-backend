package gmao_backend.service.impl;

import gmao_backend.dto.CloseInterventionRequest;
import gmao_backend.dto.CreateInterventionRequest;
import gmao_backend.dto.InterventionResponse;
import gmao_backend.dto.UpdateInterventionRequest;
import gmao_backend.entity.Intervention;
import gmao_backend.entity.Role;
import gmao_backend.entity.Status;
import gmao_backend.entity.User;
import gmao_backend.exception.BadRequestException;
import gmao_backend.exception.ResourceNotFoundException;
import gmao_backend.mapper.InterventionMapper;
import gmao_backend.repository.InterventionRepository;
import gmao_backend.repository.UserRepository;
import gmao_backend.service.InterventionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InterventionServiceImpl implements InterventionService {
    private final InterventionRepository interventionRepository;
    private final UserRepository userRepository;
    private final InterventionMapper interventionMapper;

    @Override
    public InterventionResponse create(CreateInterventionRequest request) {
        User technician = userRepository.findById(request.getTechnicianId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Technicien introuvable avec l'id : " + request.getTechnicianId()));

        if (technician.getRole() != Role.TECHNICIAN) {
            throw new BadRequestException("L'utilisateur spécifié n'est pas un technicien");
        }

        Intervention intervention = Intervention.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .clientName(request.getClientName())
                .clientPhone(request.getClientPhone())
                .clientAddress(request.getClientAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .status(Status.TODO)
                .technician(technician)
                .startDate(LocalDateTime.now())
                .build();

        Intervention savedIntervention = interventionRepository.save(intervention);
        return interventionMapper.toResponse(savedIntervention);
    }

    @Override
    @Transactional
    public List<InterventionResponse> getAll() {
        return interventionMapper.toResponseList(
                interventionRepository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    @Transactional
    public List<InterventionResponse> getByTechnician(Long technicianId) {
        if (!userRepository.existsById(technicianId)) {
            throw new ResourceNotFoundException("Technicien introuvable avec l'id : " + technicianId);
        }
        return interventionMapper.toResponseList(
                interventionRepository.findByTechnicianId(technicianId));
    }

    @Override
    public InterventionResponse update(Long id, UpdateInterventionRequest request) {
        Intervention intervention = findInterventionById(id);

        if (request.getReport() != null) {
            intervention.setReport(request.getReport());
        }
        if (request.getStatus() != null) {
            intervention.setStatus(request.getStatus());
        }

        Intervention updatedIntervention = interventionRepository.save(intervention);
        return interventionMapper.toResponse(updatedIntervention);
    }

    @Override
    public InterventionResponse changeStatus(Long id, Status status) {
        Intervention intervention = findInterventionById(id);
        intervention.setStatus(status);
        Intervention updatedIntervention = interventionRepository.save(intervention);
        return interventionMapper.toResponse(updatedIntervention);
    }

    @Override
    public InterventionResponse closeIntervention(Long id, CloseInterventionRequest request) {
        Intervention intervention = findInterventionById(id);

        if (Boolean.FALSE.equals(request.getInterventionCompleted())) {
            throw new BadRequestException("L'intervention doit être marquée comme réalisée pour être clôturée");
        }

        if (request.getEndDate() == null) {
            throw new BadRequestException("La date de fin est obligatoire");
        }

        if (request.getReport() == null || request.getReport().isBlank()) {
            throw new BadRequestException("Le rapport technique est obligatoire pour clôturer l'intervention");
        }

        intervention.setInterventionCompleted(true);
        intervention.setEndDate(request.getEndDate());
        intervention.setReport(request.getReport());
        intervention.setStatus(Status.DONE);

        Intervention closedIntervention = interventionRepository.save(intervention);
        return interventionMapper.toResponse(closedIntervention);
    }

    private Intervention findInterventionById(Long id) {
        return interventionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Intervention introuvable avec l'id : " + id));
    }
}
