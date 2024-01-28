package com.example.order.Member.service;

import com.example.order.Member.domain.Member;
import com.example.order.Member.dto.requestDto.MemberCreateReqDto;
import com.example.order.Member.dto.requestDto.MemberOrderingsReqDto;
import com.example.order.Member.dto.responseDto.MembersResDto;
import com.example.order.Member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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

    public MemberOrderingsReqDto MemberOrderings(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        MemberOrderingsReqDto memberOrderingsReqDto = new MemberOrderingsReqDto();
        memberOrderingsReqDto.setId(member.getId());
        memberOrderingsReqDto.setOrdering(member.getOrdering());
        return memberOrderingsReqDto;
    }
}
