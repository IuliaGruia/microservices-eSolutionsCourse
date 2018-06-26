package ro.microservice.inventory.repositories;

import ro.microservice.inventory.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Collection<Product> findByCode(final String code);
}
