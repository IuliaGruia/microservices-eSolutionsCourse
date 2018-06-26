package ro.microservice.store.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    @ManyToOne
    private Category category;
}
