package ro.microservice.store.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import ro.microservice.store.models.InventoryModel;

import java.math.BigDecimal;

@FeignClient(name = "inventory-service", fallbackFactory = InventoryClientFallbackFactory.class)
public interface InventoryClient {

    @GetMapping(value="/products/{code}")
    InventoryModel getProductInventory(@PathVariable("code") final String code);

}
