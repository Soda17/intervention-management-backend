package gmao_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private final String uploadDir = "uploads/interventions/";

    public StoredFile storeFile(MultipartFile file, Long interventionId) {

        try {
            String folderPath = uploadDir + interventionId + "/";
            Files.createDirectories(Paths.get(folderPath));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(folderPath + fileName);

            Files.write(filePath, file.getBytes());

            return new StoredFile(
                    fileName,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize(),
                    filePath.toString()
            );

        } catch (IOException e) {
            throw new RuntimeException("Erreur stockage fichier", e);
        }
    }

    public void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("Erreur suppression fichier", e);
        }
    }

    // DTO interne
    public record StoredFile(
            String fileName,
            String originalFileName,
            String fileType,
            long fileSize,
            String filePath
    ) {}
}
