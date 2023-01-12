package org.petstore.resources;

import org.petstore.pojo.AddPet;
import org.petstore.pojo.CategoryDetailsList;
import org.petstore.pojo.TagsDetailsList;

import java.util.ArrayList;

public class TestDataBuild {

    public AddPet addPetPayload(){
        AddPet addPetRequestPayLoad=new AddPet();
        CategoryDetailsList category=new CategoryDetailsList();
        category.setId(1);
        category.setName("Dogs");
        ArrayList<String> photoUrls=new ArrayList<>();
        photoUrls.add("test");
        ArrayList<TagsDetailsList> tags=new ArrayList<>();
        TagsDetailsList tags1=new TagsDetailsList();
        tags1.setId(1);
        tags1.setName("tags");
        tags.add(tags1);
        addPetRequestPayLoad.setId(4313);
        addPetRequestPayLoad.setCategory(category);
        addPetRequestPayLoad.setPhotoUrls(photoUrls);
        addPetRequestPayLoad.setName("Nutella");
        addPetRequestPayLoad.setTags(tags);
        addPetRequestPayLoad.setStatus("available");

        return addPetRequestPayLoad;
    }



}
