package gmao_backend.dto;

import gmao_backend.entity.Status;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInterventionRequest {
    @Size(max = 10000, message = "Le rapport ne doit pas dépasser 10000 caractères")
    private String report;

    private Status status;
}
