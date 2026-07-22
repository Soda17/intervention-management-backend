package gmao_backend.dto;

import gmao_backend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterventionResponse {
    private Long id;
    private String title;
    private String description;
    private String clientName;
    private String clientPhone;
    private String clientAddress;
    private Double latitude;
    private Double longitude;
    private Status status;
    private String report;
    private Boolean interventionCompleted;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse technician;
    private List<AttachementResponse> attachments;
}
