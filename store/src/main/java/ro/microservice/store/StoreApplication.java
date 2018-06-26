package ro.microservice.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;
import ro.microservice.store.entities.Category;
import ro.microservice.store.entities.Product;
import ro.microservice.store.repositories.CategoryRepository;
import ro.microservice.store.repositories.ProductRepository;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}

@Component
class DummyData implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public void run(final String... args){
		final Category category = categoryRepository.save(
				Category.builder()
						.name("Test Categ").build()
		);
		productRepository.save(
				Product.builder()
						.code("product1")
						.name("test")
						.category(category)
						.build()
		);
	}
}