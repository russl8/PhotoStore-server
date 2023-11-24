package com.drivec.services;

import com.drivec.models.Photo;
import com.drivec.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;


    public String addFile(String userId, String title, MultipartFile file) throws IOException {
        try {
            Photo photo = new Photo(userId, title);
            photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            photo = photoRepo.insert(photo);
            return photo.getPhotoId();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }

    public List<Photo> getAllPhotos() {
        return photoRepo.findAll();
    }

    public List<Photo> getAllPhotosByUser(String id) {
        return photoRepo.getAllPhotosByUser(id);
    }
}
