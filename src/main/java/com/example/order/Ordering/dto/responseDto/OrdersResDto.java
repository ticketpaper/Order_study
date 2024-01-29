package com.example.order.Ordering.dto.responseDto;

import com.example.order.Member.domain.Member;
import com.example.order.Ordering.domain.OrderStatus;
import lombok.Data;

@Data
public class OrdersResDto {
    private Long id;
    private Member member;
    private OrderStatus orderStatus;


}
