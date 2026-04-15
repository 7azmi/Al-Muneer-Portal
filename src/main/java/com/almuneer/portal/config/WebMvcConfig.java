package com.almuneer.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${app.upload.gallery-dir}")
    private String galleryDir;

    @Value("${app.upload.proofs-dir}")
    private String proofsDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve uploaded gallery images
        registry.addResourceHandler("/uploads/gallery/**")
                .addResourceLocations("file:" + galleryDir + "/");

        // Serve uploaded payment proofs (admin only, but path is mapped)
        registry.addResourceHandler("/uploads/proofs/**")
                .addResourceLocations("file:" + proofsDir + "/");
    }
}
