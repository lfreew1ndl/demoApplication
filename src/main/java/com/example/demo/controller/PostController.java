package com.example.demo.controller;

import com.example.demo.service.ImageService;
import com.example.demo.service.model.DetailedPicture;
import com.example.demo.service.model.Page;
import com.example.demo.service.model.SearchModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("")
public class PostController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public Page getAll(@RequestParam("page") Integer page) {
        return imageService.getAllPictures(page);
    }

    @RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
    public DetailedPicture get(@PathVariable("id") String id) {
        return imageService.getImage(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<DetailedPicture> search(@RequestParam String searchJson) {
        SearchModel searchModel = new Gson().fromJson(searchJson, SearchModel.class);
        return imageService.findImage(searchModel);
    }
}
