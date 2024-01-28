package com.example.order.Member.controller;

import com.example.order.Member.domain.Member;
import com.example.order.Member.dto.requestDto.MemberCreateReqDto;
import com.example.order.Member.dto.requestDto.MemberOrderingsReqDto;
import com.example.order.Member.dto.responseDto.MembersResDto;
import com.example.order.Member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/new")
    @ResponseBody
    public Member MemberCreate(MemberCreateReqDto memberCreateReqDto) {
        Member member = memberService.MemberCreate(memberCreateReqDto);
        return member;
    }

    @GetMapping("/members")
    @ResponseBody
    public List<MembersResDto> Members() {
        List<MembersResDto> members = memberService.Members();
        return members;
    }

    @GetMapping("/member/{id}/orders")
    @ResponseBody
    public MemberOrderingsReqDto memberOrderings(@PathVariable Long id) {
        MemberOrderingsReqDto memberOrderingsReqDto = memberService.MemberOrderings(id);
        return memberOrderingsReqDto;
    }
}
