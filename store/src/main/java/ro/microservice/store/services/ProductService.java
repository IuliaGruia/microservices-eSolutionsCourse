package ro.microservice.store.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.microservice.store.clients.InventoryClient;
import ro.microservice.store.mappers.ProductMapper;
import ro.microservice.store.models.InventoryModel;
import ro.microservice.store.models.ProductModel;
import ro.microservice.store.repositories.ProductRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public ProductService(final ProductRepository productRepository, final InventoryClient inventoryClient){
        this.productRepository = productRepository;
        this.inventoryClient = inventoryClient;

    }

    public Collection<ProductModel> getByCategory(long categId) {
       return productRepository.findByCategoryId(categId).stream()
               .map(p-> {
                    final InventoryModel inventoryModel = inventoryClient.getProductInventory(p.getCode());
                    return ProductMapper.toModel(p, inventoryModel);
               })
               .collect(Collectors.toList());
    }
}
