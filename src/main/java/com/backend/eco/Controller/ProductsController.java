package com.backend.eco.Controller;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    //returns all
    @GetMapping("/products")
       public ResponseEntity< List<Products>> get(){
          return ResponseEntity.ok(ps.getProducts()) ;
       }
   //return by id
   @GetMapping("/products/{id}")
   public ResponseEntity< Products> getId(@PathVariable int id){
      Products prod=ps.getProd(id);
      if(prod!=null){
      return  ResponseEntity.ok(prod);
      }
      else return ResponseEntity.notFound().build();
   }
   //delete    
   @DeleteMapping("/products/{id}")
   public void Delete(@PathVariable int id){
             ps.Delete(id);
   } 
   //update 
   @PutMapping("/products")
   public void Update(@RequestBody Products prod){
      ps.Update(prod);
   }


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
}
