package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import com.bridgelabz.bookstorebackend.model.OrderData;

import java.util.List;

public interface IOrderService {
    OrderData createOrderData(String token, OrderDTO orderDTO, int bookId);

    OrderData cancelOrder(String token, int orderId);

    List<OrderData> getAllOrdersOfUser(String token);

    List<OrderData> findAllOrders();
}
