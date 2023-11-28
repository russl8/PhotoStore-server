package com.drivec.controllers;

import com.drivec.models.Photo;
import com.drivec.services.PhotoService;
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

    /**
     * Add a photo to document.
     *
     * @param userid the id of the user who uploaded the photo
     * @param title  title of the image.
     * @param image  the image file.
     * @param model  db model of the photo
     * @return the id of uploaded photo document.
     * @throws IOException if photo cannot be uploaded.
     */
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.POST})
    @PostMapping("/photo")
    @ResponseStatus(HttpStatus.CREATED)
    public String addPhoto(@RequestParam String userid, @RequestParam String title, @RequestParam MultipartFile image, Model model) throws IOException {
        //test with sample userid
        userid = "6552e9e91bf5e44bccb5b5f8";
        return photoService.addFile(userid, title, image);
    }

    /**
     * Delete a photo given id.
     *
     * @param id the id of photo to be deleted.
     * @return a string saying whether not the photo has been deleted.
     */
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.DELETE})
    @DeleteMapping("/photo/{id}")
    public String deletePhoto(@PathVariable String id) {
        return photoService.deletePhoto(id) ? "Photo " + id + " deleted" : "Photo " + id + " could not be deleted";
    }

    /**
     * Fetch one photo given id.
     *
     * @param id of the photo that client wants to retrieve
     * @return a photo object containing title, image, id
     */
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/photo/{id}")
    public ResponseEntity<Map<String, String>> getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
        if (photo != null) {
            return ResponseEntity.ok(createResponse(photo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Fetch all posts made by every user.
     *
     * @return a list of all photos in the photo collection
     */
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/photo/all")
    public List<ResponseEntity<Map<String, String>>> getAllPhotos() {
        List<ResponseEntity<Map<String, String>>> list = new ArrayList<>();
        List<Photo> photoList = photoService.getAllPhotos();

        for (Photo photo : photoList) {
            list.add(ResponseEntity.ok(createResponse(photo)));
        }

        return list;
    }


    /**
     * Fetch all posts created by a user.
     *
     * @param id the id of the user.
     * @return a list of all photos uploaded by a certain user
     */
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/user/{id}")
    public List<ResponseEntity<Map<String, String>>> getAllPhotosByUser(@PathVariable String id) {
        List<ResponseEntity<Map<String, String>>> list = new ArrayList<>();
        List<Photo> photoList = photoService.getAllPhotosByUser(id);

        for (Photo photo : photoList) {
            list.add(ResponseEntity.ok(createResponse(photo)));
        }

        return list;
    }

    /**
     * Method that creates a response map of a given photo
     * @param photo a photo object
     * @return a response of the photo containing its information
     */
    public Map<String, String> createResponse(Photo photo) {
        Map<String, String> response;
        response = new HashMap<>();
        response.put("title", photo.getTitle());
        response.put("image", Base64.getEncoder().encodeToString(photo.getPhoto().getData()));
        response.put("photoId", photo.getPhotoId());
        return response;
    }


}
