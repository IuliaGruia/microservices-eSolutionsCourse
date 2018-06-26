package ro.microservice.store.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.microservice.store.entities.Category;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ProductModel {

    private String code;

    private String name;

    private Category category;

    private BigDecimal price;

}
