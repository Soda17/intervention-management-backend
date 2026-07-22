package gmao_backend.service;

import gmao_backend.dto.AttachementResponse;
import gmao_backend.entity.AttachementType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {
    List<AttachementResponse> upload(Long interventionId, List<MultipartFile> files, AttachementType attachmentType);

    List<AttachementResponse> getAttachments(Long interventionId);

    void deleteAttachment(Long attachmentId);
}
