package gmao_backend.mapper;

import gmao_backend.dto.InterventionResponse;
import gmao_backend.entity.Intervention;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InterventionMapper {
    private final UserMapper userMapper;
    private final AttachmentMapper attachmentMapper;

    public InterventionResponse toResponse(Intervention intervention) {
        if (intervention == null) {
            return null;
        }
        return InterventionResponse.builder()
                .id(intervention.getId())
                .title(intervention.getTitle())
                .description(intervention.getDescription())
                .clientName(intervention.getClientName())
                .clientPhone(intervention.getClientPhone())
                .clientAddress(intervention.getClientAddress())
                .latitude(intervention.getLatitude())
                .longitude(intervention.getLongitude())
                .status(intervention.getStatus())
                .report(intervention.getReport())
                .interventionCompleted(intervention.getInterventionCompleted())
                .startDate(intervention.getStartDate())
                .endDate(intervention.getEndDate())
                .createdAt(intervention.getCreatedAt())
                .updatedAt(intervention.getUpdatedAt())
                .technician(userMapper.toResponse(intervention.getTechnician()))
                .attachments(attachmentMapper.toResponseList(intervention.getAttachments()))
                .build();
    }

    public InterventionResponse toResponseWithoutAttachments(Intervention intervention) {
        if (intervention == null) {
            return null;
        }
        return InterventionResponse.builder()
                .id(intervention.getId())
                .title(intervention.getTitle())
                .description(intervention.getDescription())
                .clientName(intervention.getClientName())
                .clientPhone(intervention.getClientPhone())
                .clientAddress(intervention.getClientAddress())
                .latitude(intervention.getLatitude())
                .longitude(intervention.getLongitude())
                .status(intervention.getStatus())
                .report(intervention.getReport())
                .interventionCompleted(intervention.getInterventionCompleted())
                .startDate(intervention.getStartDate())
                .endDate(intervention.getEndDate())
                .createdAt(intervention.getCreatedAt())
                .updatedAt(intervention.getUpdatedAt())
                .technician(userMapper.toResponse(intervention.getTechnician()))
                .attachments(Collections.emptyList())
                .build();
    }

    public List<InterventionResponse> toResponseList(List<Intervention> interventions) {
        if (interventions == null) {
            return Collections.emptyList();
        }
        return interventions.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
