package gmao_backend.dto;

import gmao_backend.entity.AttachementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachementResponse {

    private Long id;

    private String fileName;

    private String originalFileName;

    private String fileType;

    private Long fileSize;

    private String filePath;

    private String fileUrl;

    private AttachementType attachmentType;

    private LocalDateTime uploadedAt;

    private Long interventionId;
}
