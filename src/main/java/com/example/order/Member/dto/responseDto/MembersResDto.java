package com.example.order.Member.dto.responseDto;

import com.example.order.Member.domain.Role;
import lombok.Data;

@Data
public class MembersResDto {
    private Long id;
    private String name;
    private Role role;
}
