package com.almuneer.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${app.upload.gallery-dir}")
    private String galleryDir;

    @Value("${app.upload.proofs-dir}")
    private String proofsDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Resolve paths relative to the JVM working directory so they are
        // always absolute — avoids 404s when the app is launched from a
        // directory other than the project root.
        String absoluteGallery = toAbsoluteFileUrl(galleryDir);
        String absoluteProofs  = toAbsoluteFileUrl(proofsDir);

        // Serve uploaded gallery images
        registry.addResourceHandler("/uploads/gallery/**")
                .addResourceLocations(absoluteGallery);

        // Serve uploaded payment proofs (admin only, but path is mapped)
        registry.addResourceHandler("/uploads/proofs/**")
                .addResourceLocations(absoluteProofs);
    }

    private String toAbsoluteFileUrl(String dir) {
        Path path = Paths.get(dir);
        if (!path.isAbsolute()) {
            path = Paths.get(System.getProperty("user.dir")).resolve(dir);
        }
        return "file:" + path.toAbsolutePath().toString() + "/";
    }
}
