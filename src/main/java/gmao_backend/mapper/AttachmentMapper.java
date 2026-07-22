package gmao_backend.mapper;

import gmao_backend.dto.AttachementResponse;
import gmao_backend.entity.Attachement;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttachmentMapper {


    public AttachementResponse toResponse(Attachement attachment) {


        if (attachment == null) {
            return null;
        }


        String fileUrl =
                "http://192.168.11.255:8080/"
                        +
                        attachment.getFilePath()
                                .replace("\\", "/");



        return AttachementResponse.builder()

                .id(attachment.getId())

                .fileName(attachment.getFileName())

                .originalFileName(attachment.getOriginalFileName())

                .fileType(attachment.getFileType())

                .fileSize(attachment.getFileSize())

                .filePath(attachment.getFilePath())

                .fileUrl(fileUrl)

                .attachmentType(attachment.getAttachmentType())

                .uploadedAt(attachment.getUploadedAt())

                .interventionId(
                        attachment.getIntervention().getId()
                )

                .build();

    }





    public List<AttachementResponse> toResponseList(
            List<Attachement> attachments
    ) {


        if (attachments == null) {

            return Collections.emptyList();

        }


        return attachments.stream()

                .map(this::toResponse)

                .collect(Collectors.toList());

    }

}