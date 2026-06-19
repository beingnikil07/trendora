package com.project.trendora.Repository;

import com.project.trendora.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByName(String name);

}
