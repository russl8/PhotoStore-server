package com.drivec.controllers;

import com.drivec.models.Photo;
import com.drivec.services.PhotoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@RestController
@RequestMapping
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)     //@RequestParam String userId,
    public String addPhoto(@RequestParam String title, @RequestParam MultipartFile image, Model model) throws IOException {
        System.out.println("Received userId: " + "1234");
        System.out.println("Received title: " + title);
        System.out.println("Received image: " + image.getOriginalFilename());
        return photoService.addFile("1234", title, image);
    }

    @GetMapping("/add/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getPhoto().getData()));
        return "photo" + id;
    }


}
