package com.evliion.ev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evliion.ev.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	 List<Product> findByStationId(Long userId);
}