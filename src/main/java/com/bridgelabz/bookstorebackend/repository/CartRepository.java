package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartData, Integer> {
}
