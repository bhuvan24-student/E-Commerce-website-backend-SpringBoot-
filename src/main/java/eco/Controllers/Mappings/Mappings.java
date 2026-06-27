package eco.Controllers.Mappings;
import eco.Controllers.Services.Products;
import eco.Controllers.Services.ProductsList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class Mappings {
    ProductsList products;
    public Mappings( ProductsList products) {
            this.products=products;
    }

    //mapping to call all the products
    @GetMapping("/products")
    public ResponseEntity<List<Products>> callAll(){
       List<Products> list=products.ProductsList();
       if(list==null){
           System.out.println("Nothing");
       }
        return ResponseEntity.ok(list);
    }

    //mapping for calling products with id
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> callById(@PathVariable int id){
        Products prod=products.ProductsById(id);
        if(prod==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prod);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> DeleteProduct(@PathVariable int id){
        boolean delete=products.DeleteProduct(id);
        if(delete!=true){
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok(true);
    }

    @DeleteMapping("image/{id}")
    public ResponseEntity<?> DeleteImage(@PathVariable int id){
        try{
            products.DeleteImage(id);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/products/add")
    public ResponseEntity<?> AddingProducts(Products prod){
          try{
              products.AddProducts(prod);
              return ResponseEntity.ok().build();
          }
          catch(Exception e){
              return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/products/addwithimage")
    public ResponseEntity<?> AddingProductswithImage(@RequestPart Products prod,@RequestPart MultipartFile image) throws IOException {
        try{
            products.AddWithImage(prod,image);
            return ResponseEntity.ok("Successfully added");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/products")
    public ResponseEntity<?> UpdatingProduct(@RequestPart Products prod){
        try{
            products.UpdateProducts(prod);
            return ResponseEntity.ok("successfully updated");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/products/image")
    public ResponseEntity<?> UpdatingProductsWithImage(@RequestPart Products prod, @RequestPart MultipartFile image) throws IOException{
        try{
            products.UpdatewithImage(prod,image);
            return ResponseEntity.ok("updated");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
