package com.almuneer.portal.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Component
public class FileUploadUtil {

    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png");
    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

    @Value("${app.upload.gallery-dir}")
    private String galleryDir;

    @Value("${app.upload.proofs-dir}")
    private String proofsDir;

    public String saveGalleryImage(MultipartFile file) throws IOException {
        validateImage(file);
        return saveFile(file, galleryDir);
    }

    public String savePaymentProof(MultipartFile file) throws IOException {
        validateImage(file);
        return saveFile(file, proofsDir);
    }

    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
    }

    private void validateImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if (file.getSize() > MAX_SIZE) {
            throw new IllegalArgumentException("File size exceeds 5MB limit");
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("Only JPG and PNG files are allowed");
        }
    }

    private String saveFile(MultipartFile file, String uploadDir) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".jpg";
        String uniqueName = UUID.randomUUID() + extension;

        Path filePath = uploadPath.resolve(uniqueName);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Return only the filename — callers construct the full URL via the
        // /uploads/** resource handler; the absolute path is not needed by callers.
        return uniqueName;
    }
}
