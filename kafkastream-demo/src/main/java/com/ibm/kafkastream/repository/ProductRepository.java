package com.ibm.kafkastream.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ibm.kafkastream.model.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByName(String productName);

}
