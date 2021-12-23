package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.people.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.people.MemberDto;
import org.laurieandthegang.parkshark.api.mapper.people.MemberMapper;
import org.laurieandthegang.parkshark.domain.people.Member;
import org.laurieandthegang.parkshark.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.laurieandthegang.parkshark.domain.people.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final Validator validator;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper, Validator validator) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.validator = validator;
    }

    public MemberDto addMember(CreateMemberDto createMemberDto) {
        validator.validateRequiredFieldsNotNull(createMemberDto);

        Member member = memberMapper.mapper(createMemberDto);
        memberRepository.addMember(member);
        return memberMapper.mapper(member);

    }

    public List<MemberDto> getAllMembers() {
        return memberRepository
                .getAllMembers().stream()
                .map(member -> memberMapper.mapper(member))
                .collect(Collectors.toList());
    }
}
