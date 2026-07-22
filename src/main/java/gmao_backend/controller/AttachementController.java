package gmao_backend.controller;

import gmao_backend.dto.AttachementResponse;
import gmao_backend.entity.AttachementType;
import gmao_backend.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/interventions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AttachementController {
    private final AttachmentService attachmentService;

    @PostMapping(value = "/{id}/attachments", consumes = "multipart/form-data")
    public ResponseEntity<List<AttachementResponse>> uploadAttachments(
            @PathVariable Long id,
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("attachmentType") AttachementType attachmentType) {
        List<AttachementResponse> responses = attachmentService.upload(id, files, attachmentType);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }

    @GetMapping("/{id}/attachments")
    public ResponseEntity<List<AttachementResponse>> getAttachments(@PathVariable Long id) {
        List<AttachementResponse> attachments = attachmentService.getAttachments(id);
        return ResponseEntity.ok(attachments);
    }

    @DeleteMapping("/attachments/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }
}
