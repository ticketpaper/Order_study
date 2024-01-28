package com.example.order.Ordering.dto.requestDto;

import com.example.order.Ordering.domain.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderNewReqDto {
    private Long id;
    private Long memberId;
    private List<Long> itemId;
    private List<Long> count;
    private OrderStatus orderStatus;
}
