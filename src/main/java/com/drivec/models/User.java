package com.drivec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId userId;
    private String userName;
    private List<Photo> photos = new ArrayList<Photo>();

    public User(String userName) {
    }

    public ObjectId getUserId() {

        return this.userId;
    }
}
