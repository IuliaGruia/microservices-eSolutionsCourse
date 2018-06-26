package ro.microservice.store.services;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ro.microservice.store.entities.Product;
import ro.microservice.store.models.InventoryModel;
import ro.microservice.store.repositories.ProductRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class InventoryReceiver {

    private final ProductRepository productRepository;

    public InventoryReceiver(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @StreamListener("stockChannel")
    public void onReceiver(final Message<InventoryModel> message){
        InventoryModel inventoryModel = message.getPayload();

        Collection<Product> products = productRepository.findByCode(inventoryModel.getCode()).stream()
            .map(p -> {
                p.setIsPublished(inventoryModel.getStock() > 0);
                return p;
            }).collect(Collectors.toList());

        productRepository.save(products);
    }
}
