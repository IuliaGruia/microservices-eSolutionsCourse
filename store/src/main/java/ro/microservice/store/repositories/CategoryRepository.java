package ro.microservice.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.microservice.store.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
