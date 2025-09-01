package org.example.ytlearning.service;

import org.example.ytlearning.dto.OrderRequest;
import org.example.ytlearning.dto.OrderResponse;
import org.example.ytlearning.dto.OrderStatusRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderResponse create(OrderRequest orderRequest);

    OrderResponse update(OrderStatusRequest orderStatusRequest);


}
