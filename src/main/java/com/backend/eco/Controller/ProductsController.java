package com.backend.eco.Controller;
import java.io.IOException;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.eco.Model.Products;
import com.backend.eco.Service.ProductsService;


import org.springframework.http.*;
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductsController {
   ProductsService ps;
     ProductsController(ProductsService ps){
      this. ps=ps;
    }


    //returns all the products in the database
    @GetMapping("/products")
     public ResponseEntity<List<Products>> getall(){
          List<Products> have=ps.getProducts();
          if(have!=null){
            return ResponseEntity.ok(have);
          }
          else{
           return  null;
          }
     }
 
       //return a specific product with the given id
   @GetMapping("/products/{id}")
   public ResponseEntity<Products> getbyid(@PathVariable int id){
      Products have=ps.getProd(id);
      if(have!=null)return ResponseEntity.ok(have);
      else return ResponseEntity.notFound().build();
   }
 
 
   //delete's a product with the given id in the database
   @DeleteMapping("/products/{id}")
   public void Delete(@PathVariable int id){
             ps.Delete(id);
   } 
 
 
   //update's the products details in the database
   @PutMapping("/products")
   public ResponseEntity<?> Update(@RequestBody Products prod){
         ps.Update(prod);
         return ResponseEntity.ok().build();
   }


  //adding of new products along with the image of the products
   @PostMapping("/products")
   public ResponseEntity<?> Addimage(@RequestPart Products prod, @RequestPart MultipartFile image){
      try{
         Products p1=  ps.Addprod(prod,image);
         return ResponseEntity.ok( p1);
      }
      catch(Exception e){
           return ResponseEntity.internalServerError().body(e.getMessage());
      }
   }

   //shows the image of the products with the id product id
   @GetMapping("/products/{id}/image")
   public ResponseEntity<byte[]> ImageFile(@PathVariable int id){
      Products product=ps.getProd(id);
      byte[] imagefile=product.getImage_date();
       return ResponseEntity.ok()
            .contentType(MediaType.valueOf(product.getImage_type()))
            .body(imagefile);  
   }
   
   //updating the product inculding the image 
   @PutMapping("/products/")
   public ResponseEntity<String> Updateproduct(@RequestPart Products prod,@RequestPart MultipartFile imagfile){
      Products check=null;
      try{
      check=ps.Updateprod(prod, imagfile);
      }
      catch(IOException e){
         throw new RuntimeException(e);
      }
      if(check!=null){
         return ResponseEntity.ok("Updated successfully");
      }
      else{
         return ResponseEntity.badRequest().build();
      }
   }

   //by any keyword
    @GetMapping("/products/search{keyword}")
     public ResponseEntity<List<Products>> getbykeyword(@RequestParam String keyword){
        
         List<Products> have=ps.searchbykeyword(keyword);
         if(have!=null){
            return ResponseEntity.ok(have);
         } 
         else{
            return ResponseEntity.notFound().build();
         }
     }
}
