package ro.microservice.inventory.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.microservice.inventory.models.ProductModel;
import ro.microservice.inventory.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getProductByCode(@PathVariable("code")final String code){
        return productService.findByCode(code)
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<?> saveProduct(@RequestBody final ProductModel product){
        return ResponseEntity.ok(productService.save(product));
    }
}
