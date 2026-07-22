package gmao_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloseInterventionRequest {
    @NotNull(message = "Le champ intervention réalisée est obligatoire")
    private Boolean interventionCompleted;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDateTime endDate;

    @NotBlank(message = "Le rapport technique est obligatoire")
    @Size(max = 10000, message = "Le rapport ne doit pas dépasser 10000 caractères")
    private String report;
}
