package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.CartDTO;
import com.bridgelabz.bookstorebackend.model.CartData;

import java.util.List;

public interface ICartService {
    CartData addToCart(String token, int bookId, CartDTO cartDTO);

    List<CartData> getAllCart(String token);

    void removeCart(String token, int cartId);

    CartData updateCartQuantity(String token, int cartId, int quantity);
}
