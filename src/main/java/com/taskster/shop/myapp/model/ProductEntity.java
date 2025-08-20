package com.taskster.shop.myapp.model;


import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "products")
public class ProductEntity {

    @Id
    private String id;

    @Indexed(unique = true) 
    private String productId;

    private String name;
    private float price;
    private String img;
    private String category;
    private String description;
    private List<String> color;
    private List<String> size;
    private List<String> imageSet;

    private Offer offer;
}
