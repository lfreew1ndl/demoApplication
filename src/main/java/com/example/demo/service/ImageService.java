package com.example.demo.service;


import com.example.demo.service.model.DetailedPicture;
import com.example.demo.service.model.Page;
import com.example.demo.service.model.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImageService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable("images")
    public Page getAllPictures(Integer page) {
        System.out.println(page);
        if (page == null) page = 1;
        return restClient.get("images?page=" + page + "", Page.class);
    }

    @Cacheable("image")
    public DetailedPicture getImage(String id) {
        return restClient.get("images/" + id, DetailedPicture.class);
    }

    @Cacheable("search")
    public List<DetailedPicture> findImage(SearchModel searchModel) {
        CaffeineCache imageCache = (CaffeineCache) cacheManager.getCache("image");
        Map<Object, Object> map = imageCache.getNativeCache().asMap();
        ArrayList<Object> collect = new ArrayList<>(map.values());

        Stream<DetailedPicture> pictureStream = collect.stream()
                .map(object -> (DetailedPicture) object);


        if (SearchModel.EXACT.equals(searchModel.getSearchTypeAuthor())){
            pictureStream = pictureStream.filter(value -> value.getAuthor().equals(searchModel.getAuthor()));
        } else if (SearchModel.CONTAINS.equals(searchModel.getSearchTypeAuthor())) {
            pictureStream = pictureStream.filter(value -> value.getAuthor().contains(searchModel.getAuthor()));
        }

        if (SearchModel.EXACT.equals(searchModel.getSearchTypeCamera())){
            pictureStream = pictureStream.filter(value -> value.getCamera().equals(searchModel.getCamera()));
        } else if (SearchModel.CONTAINS.equals(searchModel.getSearchTypeCamera())) {
            pictureStream = pictureStream.filter(value -> value.getCamera().contains(searchModel.getCamera()));
        }

        if (SearchModel.EXACT.equals(searchModel.getSearchTypeTags())){
            pictureStream = pictureStream.filter(value -> value.getTags().equals(searchModel.getTags()));
        } else if (SearchModel.CONTAINS.equals(searchModel.getSearchTypeTags())) {
            pictureStream = pictureStream.filter(value -> value.getTags().contains(searchModel.getTags()));
        }
        return pictureStream.collect(Collectors.toList());
    }
}
