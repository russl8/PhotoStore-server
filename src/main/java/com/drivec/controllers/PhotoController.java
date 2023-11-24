package com.drivec.controllers;

import com.drivec.models.Photo;
import com.drivec.services.PhotoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping("/photo")
    @ResponseStatus(HttpStatus.CREATED)     //@RequestParam String userId,
    public String addPhoto(@RequestParam String userid, @RequestParam String title, @RequestParam MultipartFile image, Model model) throws IOException {
        //test with sample userid
        userid = "6552e9e91bf5e44bccb5b5f8";
        return photoService.addFile(userid, title, image);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/photo/{id}")
    public ResponseEntity<Map<String, String>> getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);

        if (photo != null) {
            Map<String, String> response = new HashMap<>();
            response.put("title", photo.getTitle());
            response.put("image", Base64.getEncoder().encodeToString(photo.getPhoto().getData()));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //test: return all photos created

    /**
     * @return a list of all photo-response hashmaps
     */
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/photo/all")
    public List<ResponseEntity<Map<String, String>>> getAllPhotos() {
        List<ResponseEntity<Map<String, String>>> list = new ArrayList<>();
        Map<String, String> response;
        List<Photo> photoList = photoService.getAllPhotos();

        for (Photo photo : photoList) {
            response = new HashMap<>();
            response.put("title", photo.getTitle());
            response.put("image", Base64.getEncoder().encodeToString(photo.getPhoto().getData()));
            list.add(ResponseEntity.ok(response));
        }

        return list;
    }


    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/{id}")
    public List<ResponseEntity<Map<String, String>>> getAllPhotosByUser(@PathVariable String id) {
        List<ResponseEntity<Map<String, String>>> list = new ArrayList<>();
        Map<String, String> response;
        List<Photo> photoList = photoService.getAllPhotosByUser(id);

        for (Photo photo : photoList) {
            response = new HashMap<>();
            response.put("title", photo.getTitle());
            response.put("image", Base64.getEncoder().encodeToString(photo.getPhoto().getData()));
            list.add(ResponseEntity.ok(response));
        }

        return list;
    }


}
