package com.drivec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "photos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    private String photoId; //maybe just omit and use the mongodb ObjectID to easily populate...
    @Field("user")
    private String userId;
    private Binary photo;
    private String title;

    public Photo(String userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public String getPhotoId() {
        return photoId;
    }
    public String getUserId() {
        return userId;
    }


    public Binary getPhoto() {
        return photo;
    }

    public void setImage(Binary photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
