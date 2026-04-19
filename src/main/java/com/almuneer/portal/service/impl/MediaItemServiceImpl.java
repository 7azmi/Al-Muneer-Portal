package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.MediaItem;
import com.almuneer.portal.model.enums.MediaType;
import com.almuneer.portal.repository.MediaItemRepository;
import com.almuneer.portal.service.MediaItemService;
import com.almuneer.portal.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaItemServiceImpl implements MediaItemService {

    private final MediaItemRepository mediaItemRepository;
    private final FileUploadUtil fileUploadUtil;

    @Override
    public List<MediaItem> getAllMedia() {
        return mediaItemRepository.findAllByOrderByUploadDateDesc();
    }

    @Override
    public List<MediaItem> getMediaByCategory(String category) {
        if (category == null || category.isBlank() || "all".equalsIgnoreCase(category)) {
            return getAllMedia();
        }
        return mediaItemRepository.findByCategory(category);
    }

    @Override
    public MediaItem addImage(MultipartFile file, String caption, String category) throws IOException {
        String savedPath = fileUploadUtil.saveGalleryImage(file);
        // Use the UUID-based filename from the saved path, NOT the original filename.
        // The original filename is never stored on disk, so using it as the URL would 404.
        String fileName = Paths.get(savedPath).getFileName().toString();

        MediaItem item = MediaItem.builder()
                .mediaType(MediaType.IMAGE)
                .fileName(fileName)
                .filePath(savedPath)
                .caption(caption)
                .category(category)
                .build();

        return mediaItemRepository.save(item);
    }

    @Override
    public MediaItem addYoutubeVideo(String youtubeUrl, String caption, String category) {
        MediaItem item = MediaItem.builder()
                .mediaType(MediaType.YOUTUBE)
                .youtubeUrl(youtubeUrl)
                .caption(caption)
                .category(category)
                .build();

        return mediaItemRepository.save(item);
    }

    @Override
    public void deleteMedia(Long mediaId) throws IOException {
        MediaItem item = mediaItemRepository.findById(mediaId)
                .orElseThrow(() -> new IllegalArgumentException("Media item not found: " + mediaId));

        if (item.getMediaType() == MediaType.IMAGE && item.getFilePath() != null) {
            fileUploadUtil.deleteFile(item.getFilePath());
        }

        mediaItemRepository.delete(item);
    }
}
