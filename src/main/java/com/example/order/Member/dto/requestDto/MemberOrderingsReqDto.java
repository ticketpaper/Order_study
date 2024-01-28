package com.example.order.Member.dto.requestDto;

import com.example.order.Ordering.domain.Ordering;
import lombok.Data;

import java.util.List;

@Data
public class MemberOrderingsReqDto {
    private Long id;
    private List<Ordering> ordering;
}
