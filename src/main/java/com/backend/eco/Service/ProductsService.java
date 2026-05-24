package com.backend.eco.Service;

import java.sql.*;
import java.util.*;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
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
    //return products 
    public List<Products> getProducts(){
               return repo.findAll();
    }
    //specific products 
    public Products getProd(int id){
        return repo.findById(id).orElse(null);
     }
    //add new products 
   public Products Addprod(Products prod, MultipartFile image) throws IOException {

    prod.setImage_name(image.getOriginalFilename());

    prod.setImage_type(image.getContentType());

    prod.setImage_date(image.getBytes());

    return repo.save(prod);
    }
    //update 
    public void Update(Products prod){
        repo.save(prod);
    }
    //delete 
    public void Delete(int id){
        repo.deleteById(id);
    }
}
