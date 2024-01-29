package com.example.order.Ordering.controller;

import com.example.order.Ordering.domain.Ordering;
import com.example.order.Ordering.dto.requestDto.OrderNewReqDto;
import com.example.order.Ordering.dto.responseDto.OrdersResDto;
import com.example.order.Ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderingController {
    private final OrderingService orderingService;

    @Autowired
    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrdersResDto> Orders() {
        List<OrdersResDto> orders = orderingService.Orders();
        return orders;
    }

    @PostMapping("/order/new")
    @ResponseBody
    public String orderNew(@RequestBody OrderNewReqDto orderNewReqDto) {
        orderingService.orderNew(orderNewReqDto);
        return "OK";
    }

    @GetMapping("/order/{id}/cancel")
    @ResponseBody
    public String orderCancel(@PathVariable Long id) {
        orderingService.orderCancel(id);
        return "OK";
    }
}
