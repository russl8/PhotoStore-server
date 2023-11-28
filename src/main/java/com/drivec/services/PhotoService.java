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

    /**
     *
     * @param userId id of user
     * @param title title of image
     * @param file image file
     * @return id of the uploaded file
     * @throws IOException if file cannot be uploaded
     */
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

    /**
     *
     * @param id id of the photo
     * @return return true if photo is deleted, and false otherwise.
     */
    public boolean deletePhoto(String id) {
        if(getPhoto(id) != null) {
            photoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     *
     * @param id of phoot
     * @return a photo object
     */
    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }

    /**
     *
     * @param id of specific user
     * @return list of all photos by a user
     */
    public List<Photo> getAllPhotosByUser(String id) {
        return photoRepo.getAllPhotosByUser(id);
    }
}
