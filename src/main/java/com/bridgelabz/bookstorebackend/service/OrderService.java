package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import com.bridgelabz.bookstorebackend.exception.UserRegistrationException;
import com.bridgelabz.bookstorebackend.model.BookDetails;
import com.bridgelabz.bookstorebackend.model.OrderData;
import com.bridgelabz.bookstorebackend.model.UserRegistrationData;
import com.bridgelabz.bookstorebackend.repository.BookDetailsRepository;
import com.bridgelabz.bookstorebackend.repository.OrderRepository;
import com.bridgelabz.bookstorebackend.repository.UserRegistrationRepository;
import com.bridgelabz.bookstorebackend.tokenutil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    /**
     * Autowiring UserRegistrationRepository, TokenUtil, OrderRepository, BookDetailsRepository classes
     */
    @Autowired
    private UserRegistrationRepository userRepo;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private BookDetailsRepository bookRepo;

    /**
     * This method creates new order data taking token from user
     * @param token
     * @param orderDTO
     * @param bookId
     * @return OrderData
     */
    @Override
    public OrderData createOrderData(String token, OrderDTO orderDTO, int bookId) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> user = userRepo.findById(userId);
        Optional<BookDetails> book = bookRepo.findByBookId(bookId);
        OrderData orderData = new OrderData();
        if (user.isPresent() && book.isPresent()) {
            orderData.setUserData(user.get());
            orderData.setBookDetails(book.get());
            orderData.createOrder(orderDTO);
            return orderRepo.save(orderData);
        } else throw new UserRegistrationException("User Id or Book Id is Invalid");
    }

    /**
     * This method cancels order taking token from user
     * @param token
     * @param orderId
     * @return OrderData
     */
    @Override
    public OrderData cancelOrder(String token, int orderId) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> user = userRepo.findById(userId);
        if (user.isPresent()) {
            OrderData order = orderRepo.findById(orderId).orElse(null);
            order.setCancel(true);
            return orderRepo.save(order);
        } else throw new UserRegistrationException("Invalid Order Id");
    }

    /**
     * This method shows all the data from order repository
     * @param token
     * @return list  of OrderData
     */
    @Override
    public List<OrderData> getAllOrdersOfUser(String token) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> user = userRepo.findById(userId);
        if (user.isPresent()) {
            List<OrderData> orders = orderRepo.findUserOrder(userId);
            return orders;
        } else return null;
    }

    /**
     * This method returns all datas of orders from order repository
     * @return list of OrderData
     */
    @Override
    public List<OrderData> findAllOrders() {
        boolean cancel = false;
        return orderRepo.findOrder(cancel);
    }
}
