package org.petstore.pojo;

import java.util.ArrayList;

public class Pet {
    private int id;
    private CategoryDetailsList category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<TagsDetailsList> tags;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryDetailsList getCategory() {
        return category;
    }

    public void setCategory(CategoryDetailsList category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public ArrayList<TagsDetailsList> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagsDetailsList> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
