package com.example.order.Ordering.service;

import com.example.order.Item.domain.Item;
import com.example.order.Item.repository.ItemRepository;
import com.example.order.Member.domain.Member;
import com.example.order.Member.repository.MemberRepository;
import com.example.order.OrderItem.domain.OrderItem;
import com.example.order.OrderItem.repository.OrderItemRepository;
import com.example.order.Ordering.domain.OrderStatus;
import com.example.order.Ordering.domain.Ordering;
import com.example.order.Ordering.dto.requestDto.OrderNewReqDto;
import com.example.order.Ordering.dto.responseDto.OrdersResDto;
import com.example.order.Ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderingService {
    private final OrderingRepository orderingRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderingService(OrderingRepository orderingRepository, MemberRepository memberRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.orderingRepository = orderingRepository;
        this.memberRepository = memberRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrdersResDto> Orders() {
        List<Ordering> all = orderingRepository.findAll();
        List<OrdersResDto> ordersResDtos = new ArrayList<>();
        for (Ordering ordering : all) {
            OrdersResDto ordersResDto1 = new OrdersResDto();
            ordersResDto1.setId(ordering.getId());
            ordersResDto1.setMember(ordering.getMember());
            ordersResDto1.setOrderStatus(ordering.getOrderStatus());
            ordersResDtos.add(ordersResDto1);
        }
        return ordersResDtos;
    }

    @Transactional
    public void orderNew(OrderNewReqDto orderNewReqDto) throws EntityNotFoundException{
        Member member = memberRepository.findById(orderNewReqDto.getMemberId()).orElseThrow(EntityNotFoundException::new);
        List<Long> itemId = orderNewReqDto.getItemId();
        List<Long> count = orderNewReqDto.getCount();
        Ordering ordering = Ordering.builder()
                .member(member)
                .orderStatus(OrderStatus.ORDERED)
                .build();
        orderingRepository.save(ordering);

        int i = 0;
        for (Long l : itemId) {
            Item item = itemRepository.findById(l).orElseThrow(EntityNotFoundException::new);
            int countValue = Math.toIntExact(count.get(i++));
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .quantity(countValue)
                    .ordering(ordering)
                    .build();
            ordering.stockManage(ordering, item, countValue);
            orderItemRepository.save(orderItem);
        }

    }

    @Transactional
    public void orderCancel(Long id) {
        Ordering ordering = orderingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ordering.orderUpdate(OrderStatus.CANCELED);
        List<OrderItem> allByOrdering = orderItemRepository.findAllByOrdering(ordering);
        for (OrderItem orderItem : allByOrdering) {
            Item item = orderItem.getItem();
            int quantity = orderItem.getQuantity();
            ordering.stockManage(ordering, item, quantity);
        }
    }

}
