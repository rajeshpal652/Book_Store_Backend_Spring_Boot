package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer> {
    Optional<BookDetails> findByBookId(int bookId);
}
