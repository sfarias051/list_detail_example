package com.example.sebastianfarias.demo.ListDetail.Model;

import java.io.Serializable;

public class Item implements Serializable{

    private static final long serialVersionUID = 8328136448707803168L;
    String title;
    String description;
    String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
