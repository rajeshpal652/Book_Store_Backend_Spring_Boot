package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.CartDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.CartData;
import com.bridgelabz.bookstorebackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartservice")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * Ability to show all cart details
     * @param token
     * @return ResponseEntity
     */
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllCart(@RequestHeader(name = "token") String token) {
        List<CartData> cartData = cartService.getAllCart(token);
        ResponseDTO responseDTO = new ResponseDTO("All Cart Data Retrived Successfully", cartData);
        return  new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to add book to cart
     * @param token
     * @param bookid
     * @param cartDTO
     * @return ResponseEntity
     */
    @PostMapping("/addtocart/{bookid}")
    public ResponseEntity<ResponseDTO> addToCart(@RequestHeader(name = "token") String token,
                                                 @PathVariable("bookid") int bookid,
                                                 @RequestBody CartDTO cartDTO) {
        CartData cart = cartService.addToCart(token, bookid, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added to cart Successfully", cart);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to update book quantity in cart
     * @param token
     * @param cartId
     * @param quantity
     * @return ResponseEntity
     */
    @PutMapping("/update/{cartId}/{quantity}")
    public ResponseEntity<ResponseDTO> updateCartQuantity(@RequestHeader(name = "token") String token,
                                                          @PathVariable("cartId") int cartId,
                                                          @PathVariable("quantity")int quantity) {
        CartData cartData = cartService.updateCartQuantity(token, cartId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Cart Updated Successfully", cartData);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to delete cart by cart id
     * @param token
     * @param cartId
     * @return ResponseEntity
     */
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCart(@RequestHeader(name = "token") String token,
                                                  @PathVariable("cartId") int cartId) {
        cartService.removeCart(token, cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart Removed Successfully",
                "Removed Cart Id : " + cartId);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }
}
