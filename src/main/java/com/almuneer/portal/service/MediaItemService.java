package com.almuneer.portal.service;

import com.almuneer.portal.model.MediaItem;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface MediaItemService {
    List<MediaItem> getAllMedia();
    List<MediaItem> getMediaByCategory(String category);
    MediaItem addImage(MultipartFile file, String caption, String category) throws IOException;
    MediaItem addYoutubeVideo(String youtubeUrl, String caption, String category);
    void deleteMedia(Long mediaId) throws IOException;
}
