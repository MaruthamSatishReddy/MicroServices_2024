package com.easyToBuy.Order.service;

import com.easyToBuy.Order.client.InventoryClient;
import com.easyToBuy.Order.dto.OrderRequest;
import com.easyToBuy.Order.model.Order;
import com.easyToBuy.Order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository  orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest){

        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (inStock) {
            orderRepository.save(mapToOrder(orderRequest));
        } else {

            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + "is not in Stock");
        }

    }
    private Order mapToOrder(OrderRequest orderRequest){
        Order  order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        return order;
    }
}
