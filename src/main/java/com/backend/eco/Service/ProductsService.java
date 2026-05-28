package com.backend.eco.Service;
import java.util.*;
import java.io.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.eco.Model.Products;
import com.backend.eco.Repo.ProductRepo;

@Service
public class ProductsService {
    ProductRepo repo;
       ProductsService( ProductRepo repo){
                 this.repo=repo;
       }


    //return's all the products present in the database
    public List<Products> getProducts(){
               return repo.findAll();
    }


    // returns on product with the given id 
    public Products getProd(int id){
        return repo.findById(id).orElse(null);
     }


    //add new product along with image of it 
   public Products Addprod(Products prod, MultipartFile image) throws IOException {

    prod.setImage_name(image.getOriginalFilename());
    prod.setImage_type(image.getContentType());
    prod.setImage_date(image.getBytes());

    return repo.save(prod);
    }

    //updates the existing product details 
    public void Update(Products prod){
        repo.save(prod);
    }

    //delete's product from the database (id of the products should be provided)
    public void Delete(int id){
        repo.deleteById(id);
    }

    //return's image of a product (only image and id of the product is needed)
    public byte[] image(int id){
        Products prod=repo.findById(id).orElse(null);
        byte[]image=prod.getImage_date();
        return image;
    }

    //updating the product with inculding the image 
    public Products Updateprod(Products prod, MultipartFile imagefile) throws IOException{
        prod.setImage_name(imagefile.getOriginalFilename());
        prod.setImage_type(imagefile.getContentType());
        prod.setImage_date(imagefile.getBytes());
       return  repo.save(prod);
        }
}
