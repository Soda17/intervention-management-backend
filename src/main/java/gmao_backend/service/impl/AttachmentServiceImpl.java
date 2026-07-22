package gmao_backend.service.impl;

import gmao_backend.dto.AttachementResponse;
import gmao_backend.entity.Attachement;
import gmao_backend.entity.AttachementType;
import gmao_backend.entity.Intervention;
import gmao_backend.exception.BadRequestException;
import gmao_backend.exception.ResourceNotFoundException;
import gmao_backend.mapper.AttachmentMapper;
import gmao_backend.repository.AttachementRepository;
import gmao_backend.repository.InterventionRepository;
import gmao_backend.service.AttachmentService;
import gmao_backend.service.FileStorageService;
import gmao_backend.util.FileStorageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachementRepository attachmentRepository;
    private final InterventionRepository interventionRepository;
    private final AttachmentMapper attachmentMapper;
    private final FileStorageService fileStorageService;

    @Override
    public List<AttachementResponse> upload(Long interventionId, List<MultipartFile> files, AttachementType attachmentType) {
        Intervention intervention = interventionRepository.findById(interventionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Intervention introuvable avec l'id : " + interventionId));

        if (files == null || files.isEmpty()) {
            throw new BadRequestException("Aucun fichier fourni");
        }

        if (attachmentType == null) {
            throw new BadRequestException("Le type de pièce jointe est obligatoire");
        }

        List<AttachementResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            FileStorageService.StoredFile storedFile =
                    fileStorageService.storeFile(file, interventionId);
            Attachement attachment = Attachement.builder()
                    .fileName(storedFile.fileName())
                    .originalFileName(storedFile.originalFileName())
                    .fileType(storedFile.fileType())
                    .fileSize(storedFile.fileSize())
                    .filePath(storedFile.filePath())
                    .attachmentType(attachmentType)
                    .intervention(intervention)
                    .build();

            Attachement savedAttachment = attachmentRepository.save(attachment);
            responses.add(attachmentMapper.toResponse(savedAttachment));
        }

        if (responses.isEmpty()) {
            throw new BadRequestException("Aucun fichier valide n'a été fourni");
        }

        return responses;
    }

    @Override
    @Transactional
    public List<AttachementResponse> getAttachments(Long interventionId) {
        if (!interventionRepository.existsById(interventionId)) {
            throw new ResourceNotFoundException("Intervention introuvable avec l'id : " + interventionId);
        }
        return attachmentMapper.toResponseList(
                attachmentRepository.findByInterventionId(interventionId));
    }

    @Override
    public void deleteAttachment(Long attachmentId) {
        Attachement attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pièce jointe introuvable avec l'id : " + attachmentId));

        fileStorageService.deleteFile(attachment.getFilePath());
        attachmentRepository.delete(attachment);
    }
}
