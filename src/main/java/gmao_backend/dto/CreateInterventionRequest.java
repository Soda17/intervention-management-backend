package gmao_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInterventionRequest {
    @NotBlank(message = "Le titre est obligatoire")
    @Size(max =255 ,message = "Le titre ne doit pas dépasser 255 caractères")
    private String title;

    @Size(max = 5000, message = "La description ne doit pas dépasser 5000 caractères")
    private String description;

    @NotBlank(message = "Le nom du client est obligatoire")
    @Size(max = 255, message = "Le nom du client ne doit pas dépasser 255 caractères")
    private String clientName;

    @Size(max = 20, message = "Le téléphone ne doit pas dépasser 20 caractères")
    private String clientPhone;

    @Size(max = 1000, message = "L'adresse ne doit pas dépasser 1000 caractères")
    private String clientAddress;

    private Double latitude;

    private Double longitude;

    @NotNull(message = "L'identifiant du technicien est obligatoire")
    private Long technicianId;
}
