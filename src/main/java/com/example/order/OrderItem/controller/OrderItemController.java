package com.example.order.OrderItem.controller;

import com.example.order.OrderItem.dto.responseDto.OrderItemsResDto;
import com.example.order.OrderItem.repository.OrderItemRepository;
import com.example.order.OrderItem.service.OrderItemService;
import com.example.order.Ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderitems/{id}")
    @ResponseBody
    public List<OrderItemsResDto> orderItem(@PathVariable Long id) {
        List<OrderItemsResDto> orderItemsResDtos = orderItemService.OrderItem(id);
        return orderItemsResDtos;
    }
}
