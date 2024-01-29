package com.example.order.Member.service;

import com.example.order.Item.domain.Item;
import com.example.order.Item.repository.ItemRepository;
import com.example.order.Member.domain.Member;
import com.example.order.Member.dto.requestDto.MemberCreateReqDto;
import com.example.order.Member.dto.requestDto.MemberOrderingsReqDto;
import com.example.order.Member.dto.responseDto.MembersResDto;
import com.example.order.Member.repository.MemberRepository;
import com.example.order.OrderItem.domain.OrderItem;
import com.example.order.OrderItem.repository.OrderItemRepository;
import com.example.order.Ordering.domain.OrderStatus;
import com.example.order.Ordering.domain.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, OrderItemRepository orderItemRepository, ItemRepository itemRepository) {
        this.memberRepository = memberRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
    }

    public Member MemberCreate(MemberCreateReqDto memberCreateDto) {
        Member member = Member.builder()
                .name(memberCreateDto.getName())
                .email(memberCreateDto.getEmail())
                .password(memberCreateDto.getPassword())
                .address(memberCreateDto.getAddress())
                .role(memberCreateDto.getRole())
                .build();
        Member member1 = memberRepository.save(member);

        return member1;
    }

    public List<MembersResDto> Members() {
        List<Member> all = memberRepository.findAll();
        List<MembersResDto> membersResDtos = new ArrayList<>();
        for (Member member : all) {
            MembersResDto membersResDto1 = new MembersResDto();
            membersResDto1.setId(member.getId());
            membersResDto1.setName(member.getName());
            membersResDto1.setRole(member.getRole());
            membersResDtos.add(membersResDto1);
        }
        return membersResDtos;
    }

    public MemberOrderingsReqDto MemberOrderings(Long id) throws EntityNotFoundException{
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Member Not Found"));
        MemberOrderingsReqDto memberOrderingsReqDto = new MemberOrderingsReqDto();
        List<Ordering> ordering = member.getOrdering();
        Map<String, Integer> itemNameStockMap = new HashMap<>();

        for (Ordering ordering1 : ordering) {
            if (ordering1.getOrderStatus().equals(OrderStatus.ORDERED)) {
                List<OrderItem> allByOrdering = orderItemRepository.findAllByOrdering(ordering1);
                for (OrderItem orderItem : allByOrdering) {
                    String name = orderItem.getItem().getName();
                    int quantity = orderItem.getQuantity();
                    itemNameStockMap.put(name, quantity);
                }
            }
        }
        memberOrderingsReqDto.setId(member.getId());
        memberOrderingsReqDto.setItemNameStockMap(itemNameStockMap);

        return memberOrderingsReqDto;
    }
}
