package com.example.demo.service;

import com.example.demo.service.model.DetailedPicture;
import com.example.demo.service.model.Page;
import com.example.demo.service.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class UploadScheduler {

    @Autowired
    ImageService imageService;

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRate = 200000)
    public void updateCache() {
        for (String name : cacheManager.getCacheNames()) {
            cacheManager.getCache(name).clear();            // clear cache by name
        }

        Page allPictures = imageService.getAllPictures(1);

        for (float pageNumber = 1; pageNumber <= allPictures.getPageCount(); pageNumber++) {
            Page pictures = imageService.getAllPictures((int) pageNumber);

            for (Picture picture : pictures.getPictures()) {
                imageService.getImage(picture.getId());
            }
        }
    }
}
