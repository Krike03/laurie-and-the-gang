package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.people.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.people.MemberDto;
import org.laurieandthegang.parkshark.api.mapper.people.MemberMapper;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.laurieandthegang.parkshark.domain.people.*;

import static org.laurieandthegang.parkshark.service.Validator.VALIDATOR;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class); // <- delete if never used in the future
    private MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto addMember(CreateMemberDto createMemberDto) {
        VALIDATOR.validateRequiredFieldsNotNull(createMemberDto);

        Member member = memberMapper.mapper(createMemberDto);
        memberRepository.addMember(member);
        return memberMapper.mapper(member);

    }
}
