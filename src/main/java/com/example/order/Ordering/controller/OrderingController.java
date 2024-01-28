package com.example.order.Ordering.controller;

import com.example.order.Ordering.dto.responseDto.OrdersResDto;
import com.example.order.Ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderingController {
    private final OrderingService orderingService;

    @Autowired
    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @GetMapping("/orders")
    public List<OrdersResDto> Orders() {
        List<OrdersResDto> orders = orderingService.Orders();
        return orders;
    }

    @PostMapping("/order/new")
    public void orderNew() {

    }

    @GetMapping("/order/{id}/cancle")
    public void orderCancle() {

    }
}
