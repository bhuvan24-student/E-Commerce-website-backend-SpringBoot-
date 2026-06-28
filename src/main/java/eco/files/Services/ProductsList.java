package eco.files.Services;

import eco.files.Repository.ProductRepository;
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

    //calling by keyword
    public List<Products> ProductsByKeyword(String keyword){ return repo.searchProducts(keyword);}

    //adding products along with image
    public void AddWithImage(Products prod, MultipartFile image) throws IOException {
        prod.setImage(image.getBytes());
        prod.setImage_name(image.getName());
        prod.setImage_type(image.getContentType());
        repo.save(prod);
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


