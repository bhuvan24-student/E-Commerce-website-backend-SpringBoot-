package eco.Controllers.Services;

import eco.Controllers.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductsList {
    ProductRepository repo;

    public ProductsList(ProductRepository repo) {
        this.repo = repo;
    }

    //calling all the products
    public List<Products> ProductsList() {
        return repo.findAll();
    }

    //calling by id
    public Products ProductsById(int id) {
        return repo.findById(id).orElse(null);
    }

    //adding products along with image
    public void AddWithImage(Products prod, MultipartFile image) throws IOException {
        prod.setImage(image.getBytes());
        prod.setImage_name(image.getName());
        prod.setImage_type(image.getContentType());
        repo.save(prod);
        int id=prod.getId();

    }

    //adding products with out image
    public void AddProducts(Products prod){
        repo.save(prod);
    }

    //updating the existing product with image
    public void UpdatewithImage(Products prod, MultipartFile image)throws IOException {
        prod.setImage(image.getBytes());
        prod.setImage_name(image.getName());
        prod.setImage_type(image.getContentType());
        repo.save(prod);
    }

    //update with out image
    public void UpdateProducts(Products prod){
        repo.save(prod);
    }

    //deleting a product through id
    public boolean DeleteProduct(int id){
        Products prod=repo.findById(id).orElse(null);
        if(prod!=null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    //delete image of a project
    public void DeleteImage(int id){
        Products prod=repo.findById(id).orElse(null);
        prod.setImage_type(null);
        prod.setImage(null);
        prod.setImage_name(null);
        repo.save(prod);
    }

}


