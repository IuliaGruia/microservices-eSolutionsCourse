package ro.microservice.store.clients;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import ro.microservice.store.models.InventoryModel;

import java.math.BigDecimal;

@Component
public class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient>{

    @Override
    public InventoryClient create(final Throwable throwable){
        return new InventoryClient() {
            @Override
            public InventoryModel getProductInventory(String code) {
                return InventoryModel.builder()
                    .code(code)
                    .price(BigDecimal.ZERO)
                        .build();
            }
        };

    }

}
