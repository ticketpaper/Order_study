package com.example.order.Member.dto.requestDto;

import com.example.order.Ordering.domain.Ordering;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MemberOrderingsReqDto {
    private Long id;
    private Map<String, Integer> itemNameStockMap;
}
