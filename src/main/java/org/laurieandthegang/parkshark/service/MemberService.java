package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.MemberDto;
import org.laurieandthegang.parkshark.api.mapper.MemberMapper;
import org.laurieandthegang.parkshark.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.laurieandthegang.parkshark.domain.people.*;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto addMember(CreateMemberDto createMemberDto){
        Member member = memberMapper.mapper(createMemberDto);
        memberRepository.addMember(member);
        return memberMapper.mapper(member);
    }
}
