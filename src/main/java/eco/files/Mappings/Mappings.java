package eco.files.Mappings;
import eco.files.Services.Products;
import eco.files.Services.ProductsList;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
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
    //MAPPING FOR CSRF TOKEN
    @GetMapping("/csrf")
    public CsrfToken Callcsrf(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

//    mapping for calling products with id
    @GetMapping("/products/{id:\\d+}")
    public ResponseEntity<Products> callById(@PathVariable int id){
        Products prod=products.ProductsById(id);
        if(prod==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prod);
    }

    //mapping for calling products with name or keyword
    @GetMapping("/products/search")
    public List<Products> callByName(@RequestParam String keyword){
       List< Products> prod=products.ProductsByKeyword(keyword);
        return prod;
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
    public ResponseEntity<?> AddingProducts(@RequestBody Products prod){
          try{
              products.AddProducts(prod);
              return ResponseEntity.ok().build();
          }
          catch(Exception e){
              return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/products/addwithimage")
    public ResponseEntity<?> AddWithImage(@RequestPart Products prod,@RequestPart MultipartFile image) throws IOException {
        try{
            products.AddWithImage(prod,image);
            return ResponseEntity.ok("Successfully added");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/products")
    public ResponseEntity<?> UpdatingProduct(@RequestBody Products prod){
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
