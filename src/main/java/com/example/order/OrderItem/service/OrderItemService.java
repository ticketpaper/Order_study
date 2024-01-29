package com.example.order.OrderItem.service;

import com.example.order.Item.domain.Item;
import com.example.order.Item.repository.ItemRepository;
import com.example.order.OrderItem.domain.OrderItem;
import com.example.order.OrderItem.dto.responseDto.OrderItemsResDto;
import com.example.order.OrderItem.repository.OrderItemRepository;
import com.example.order.Ordering.domain.Ordering;
import com.example.order.Ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderingRepository orderingRepository;
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, OrderingRepository orderingRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderingRepository = orderingRepository;
    }

    public List<OrderItemsResDto> OrderItem(Long id) {
        Ordering ordering = orderingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<OrderItem> allByOrdering = orderItemRepository.findAllByOrdering(ordering);
        List<OrderItemsResDto> orderItemsResDtos = new ArrayList<>();

        for (OrderItem orderItem : allByOrdering) {
            OrderItemsResDto orderItemsResDto = new OrderItemsResDto();
            orderItemsResDto.setItem_id(orderItem.getId());
            orderItemsResDto.setOrdering_id(orderItem.getOrdering().getId());
            orderItemsResDto.setQuantity(orderItem.getQuantity());

            orderItemsResDtos.add(orderItemsResDto);
        }

        return orderItemsResDtos;
    }
}
