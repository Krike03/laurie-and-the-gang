package org.laurieandthegang.parkshark.service;

import org.hibernate.PropertyValueException;
import org.laurieandthegang.parkshark.api.dto.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.MemberDto;
import org.laurieandthegang.parkshark.api.mapper.MemberMapper;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.laurieandthegang.parkshark.domain.people.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

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
        validateRequiredFieldsNotNull(createMemberDto);

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

    public void validateRequiredFieldsNotNull(CreateMemberDto createMemberDto) {
        if (createMemberDto.name() == null) {
            throw new RequiredFieldIsNullException("NAME");
        } else if (createMemberDto.address().getPostalCode() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE");
        } else if (createMemberDto.address().getPostalCode().getNumeralCode() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - NUMERAL");
        } else if (createMemberDto.address().getPostalCode().getCityLabel() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - CITY LABEL");
        } else if (createMemberDto.address().getStreetName() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NAME");
        } else if (createMemberDto.address().getStreetNumber() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NUMBER");
        } else if (createMemberDto.licensePlate().getLicenseNumber() == null) {
            throw new RequiredFieldIsNullException("LICENSE PLATE - NUMBER");
        } else if (createMemberDto.licensePlate().getCountryLabel() == null) {
            throw new RequiredFieldIsNullException("LICENSE PLATE - COUNTRY LABEL");
        } else if (createMemberDto.email() == null) {
            throw new RequiredFieldIsNullException("EMAIL");
        } else if (createMemberDto.phoneNumber() == null) {
            throw new RequiredFieldIsNullException("PHONE NUMBER");
        }
    }
}
