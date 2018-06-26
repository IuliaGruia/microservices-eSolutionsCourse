package ro.microservice.store.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservice.store.models.ProductModel;
import ro.microservice.store.services.ProductService;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private ProductService productService;

    public ProductResource(final ProductService productService){
        this.productService = Objects.requireNonNull(productService, "product service should not be null");
    }

    @GetMapping("/list/{category}")
    public Collection<ProductModel> getCategoryproducts(@PathVariable("category") final long categorId){
        return productService.getByCategory(categorId);
    }

}
