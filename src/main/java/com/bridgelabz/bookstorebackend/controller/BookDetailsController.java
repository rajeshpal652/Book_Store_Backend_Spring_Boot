package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.BookDetailsDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.BookDetails;
import com.bridgelabz.bookstorebackend.service.IBookDetailsService;
import com.bridgelabz.bookstorebackend.tokenutil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookservice")
public class BookDetailsController {

    @Autowired
    private IBookDetailsService bookService;
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * Ability to retrive all books
     * @param token
     * @return ResponseEntity
     */
    @RequestMapping(value = { "/getbooks" })
    public ResponseEntity<ResponseDTO> getAllBooks(@RequestHeader(name = "token") String token) {
        List<BookDetails> books = bookService.showAllBooks(token);
        ResponseDTO responseDTO = new ResponseDTO("All Books Retrived Successfully", books);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to retrive book by id
     * @param token
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/getbook/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@RequestHeader(name = "token") String token,
                                                   @PathVariable("id") int id) {
        BookDetails book = bookService.getBookById(token,id);
        ResponseDTO responseDTO = new ResponseDTO("Book retrived successfully", book);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to add book with details
     * @param token
     * @param bookDTO
     * @return ResponseEntity
     */
    @PostMapping("/addbook")
    public ResponseEntity<ResponseDTO> addBook(@RequestHeader(name = "token") String token,
                                               @RequestBody BookDetailsDTO bookDTO) {
        BookDetails book = bookService.addBook(token, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book added successfully", book);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to update book by id
     * @param token
     * @param id
     * @param bookDTO
     * @return ResponseEntity
     */
    @PutMapping("/updatebook/{id}")
    public ResponseEntity<ResponseDTO> updateBookById(@RequestHeader(name = "token") String token,
                                                      @PathVariable("id") int id,
                                                      @RequestBody BookDetailsDTO bookDTO) {
        BookDetails book = bookService.updateBook(token, id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book updated successfully", book);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to update book price by book id
     * @param token
     * @param id
     * @param bookPrice
     * @return ResponseEntity
     */
    @PutMapping("/updatebookprice/{id}")
    public ResponseEntity<ResponseDTO> updateBookPriceById(@RequestHeader(name = "token") String token,
                                                           @PathVariable("id") int id,
                                                           @RequestParam(value = "bookPrice") int bookPrice) {
        BookDetails book = bookService.updateBookPrice(token, id, bookPrice);
        ResponseDTO responseDTO = new ResponseDTO("Book Price updated successfully", book);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to update book quantity by book id
     * @param token
     * @param id
     * @param bookQuantity
     * @return ResponseEntity
     */
    @PutMapping("/updatebookquantity/{id}")
    public ResponseEntity<ResponseDTO> updateBookQuantityById(@RequestHeader(name = "token") String token,
                                                           @PathVariable("id") int id,
                                                           @RequestParam(value = "bookQuantity") int bookQuantity) {
        BookDetails book = bookService.updateBookQuantity(token, id, bookQuantity);
        ResponseDTO responseDTO = new ResponseDTO("Book Quantity updated successfully", book);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to delete book by book id
     * @param token
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<ResponseDTO> deleteBookById(@RequestHeader(name = "token") String token,
                                                      @PathVariable("id") int id) {
        bookService.deleteBook(token, id);
        ResponseDTO responseDTO = new ResponseDTO("Book deleted successfully", "Deleted Book ID : " + id);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

}
