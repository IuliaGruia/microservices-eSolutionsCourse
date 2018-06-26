package ro.microservice.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.stereotype.Component;
import ro.microservice.inventory.config.KafkaChannels;
import ro.microservice.inventory.entities.Product;
import ro.microservice.inventory.repositories.ProductRepository;

import java.math.BigDecimal;

@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(KafkaChannels.class)
@IntegrationComponentScan(basePackages = "ro.microservice.inventory")
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}


@Component
class DummyData implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(final String... args){

		productRepository.save(
				Product.builder()
						.code("product1")
						.price(new BigDecimal(200))
						.build()
		);
	}
}