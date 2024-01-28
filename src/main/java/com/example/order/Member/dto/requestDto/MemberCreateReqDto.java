package com.example.order.Member.dto.requestDto;

import com.example.order.Member.domain.Role;
import lombok.Data;

@Data
public class MemberCreateReqDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private Role role;
}
