package ro.microservice.inventory.services;

import org.springframework.stereotype.Service;
import ro.microservice.inventory.config.KafkaGateway;
import ro.microservice.inventory.entities.Product;
import ro.microservice.inventory.models.ProductModel;
import ro.microservice.inventory.repositories.ProductRepository;
import ro.microservice.inventory.mappers.ProductMapper;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final KafkaGateway kafkaGateway;

    public ProductService(final ProductRepository productRepository, final KafkaGateway kafkaGateway){
        this.kafkaGateway = kafkaGateway;
        this.productRepository = productRepository;

    }

    public Optional<ProductModel> findByCode(String code) {
        return productRepository.findByCode(code).stream()
                .findFirst()
                .map(p -> ProductMapper.toModel(p));

    }

    public ProductModel save(ProductModel product) {
        Product prod = productRepository.findByCode(product.getCode()).stream()
                .findFirst()
                .map(p -> {
                    Integer initStock = p.getStock();
                    p.setStock(product.getStock());
                    if(initStock != product.getStock()){
                        kafkaGateway.write(product);
                    }
                    return p;
                })
                .orElseGet(() -> ProductMapper.toEntity(product));
        return ProductMapper.toModel(productRepository.save(prod));
    }
}
