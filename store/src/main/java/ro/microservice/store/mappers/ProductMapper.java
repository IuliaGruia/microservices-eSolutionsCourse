package ro.microservice.store.mappers;

import org.springframework.http.ResponseEntity;
import ro.microservice.store.entities.Product;
import ro.microservice.store.models.InventoryModel;
import ro.microservice.store.models.ProductModel;

import java.math.BigDecimal;

public class ProductMapper {

    public static ProductModel toModel(final Product product, ResponseEntity<InventoryModel> inventoryModel){
       return ProductMapper.toModel(product, InventoryModel.builder().price(BigDecimal.ZERO).build());
    }

    public static ProductModel toModel(final Product product, final InventoryModel inventory){
        return ProductModel.builder()
                .code(product.getCode())
                .name(product.getName())
                .category(product.getCategory())
                .price(inventory.getPrice())
                .build();
    }
}