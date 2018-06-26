package ro.microservice.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.microservice.store.entities.Product;

import java.util.Collection;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Collection<Product> findByCategoryId(final long categId);
}
