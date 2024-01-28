package com.example.order.Ordering.service;

import com.example.order.Item.domain.Item;
import com.example.order.Item.repository.ItemRepository;
import com.example.order.Member.domain.Member;
import com.example.order.Member.repository.MemberRepository;
import com.example.order.OrderItem.domain.OrderItem;
import com.example.order.Ordering.domain.Ordering;
import com.example.order.Ordering.dto.requestDto.OrderNewReqDto;
import com.example.order.Ordering.dto.responseDto.OrdersResDto;
import com.example.order.Ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderingService {
    private final OrderingRepository orderingRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderingService(OrderingRepository orderingRepository, MemberRepository memberRepository, ItemRepository itemRepository) {
        this.orderingRepository = orderingRepository;
        this.memberRepository = memberRepository;
        this.itemRepository = itemRepository;
    }

    public List<OrdersResDto> Orders() {
        List<Ordering> all = orderingRepository.findAll();
        List<OrdersResDto> ordersResDtos = new ArrayList<>();
        for (Ordering ordering : all) {
            OrdersResDto ordersResDto1 = new OrdersResDto();
            ordersResDto1.setId(ordering.getId());
            ordersResDtos.add(ordersResDto1);
        }
        return ordersResDtos;
    }

    public void orderNew(OrderNewReqDto orderNewReqDto) {

        Member member = memberRepository.findById(orderNewReqDto.getMemberId()).orElse(null);
        Item item = new Item();

    }

    public void orderCancle() {

    }

}
