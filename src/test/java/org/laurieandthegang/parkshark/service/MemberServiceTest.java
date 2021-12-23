package org.laurieandthegang.parkshark.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.laurieandthegang.parkshark.api.dto.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.MemberDto;
import org.laurieandthegang.parkshark.api.mapper.MemberMapper;
import org.laurieandthegang.parkshark.domain.people.*;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.MemberRepository;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MemberServiceTest {

    private MemberRepository mockMemberRepository;
    private MemberService memberService;
    private MemberMapper memberMapper;

    @BeforeEach
    void setUp() {
        mockMemberRepository = Mockito.mock(MemberRepository.class);
        memberMapper = new MemberMapper();
        memberService = new MemberService(mockMemberRepository, memberMapper);
    }

    @Test
    void givenMembersInDatabase_whenGettingAllMembersThenAllMembersAreReturned() {

        Member member1 = new Member(
                new Name("First", "Last"),
                new Address("Sesam", "123", new PostalCode("3000", "Leuven")),
                "02/8985847",
                "wtf@wtf.com",
                new LicensePlate("1-TYR-963", "BE"));

        Member member2 = new Member(
                new Name("First2", "Last2"),
                new Address("Sesam", "123", new PostalCode("3000", "Leuven")),
                "02/8985847",
                "wtf@wtf.com",
                new LicensePlate("1-TYR-963", "BE"));


        List<Member> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);

        List<MemberDto> expectedMembersDtoList = memberList.stream()
                .map(member -> memberMapper.mapper(member))
                .collect(Collectors.toList());

        Mockito.when(mockMemberRepository.getAllMembers()).thenReturn(memberList);

        Assertions.assertThat(memberService.getAllMembers()).isEqualTo(expectedMembersDtoList);
    }

    @Test
    void givenMemberToCreate_whenRegisteringMemberNameIncorrectly_thenThrowError() {
        //GIVEN
        //WHEN
        CreateMemberDto createMemberDto = new CreateMemberDto(
                null,
                new Address("Sesam", "123", new PostalCode("3000", "Leuven")),
                "02/8985847",
                "wtf@wtf.com",
                new LicensePlate("1-TYR-963", "BE"));
        //THEN
        Assertions.assertThatExceptionOfType(RequiredFieldIsNullException.class).isThrownBy(()-> memberService.addMember(createMemberDto));
    }

    @Test
    void givenMemberToCreate_whenRegisteringMemberAddressIncorrectly_thenThrowError() {
        //GIVEN
        //WHEN
        CreateMemberDto createMemberDto = new CreateMemberDto(
                new Name("First","Last"),
                new Address("Sesam", "123",null),
                "02/8985847",
                "wtf@wtf.com",
                new LicensePlate("1-TYR-963", "BE"));
        //THEN
        Assertions.assertThatExceptionOfType(RequiredFieldIsNullException.class).isThrownBy(()-> memberService.addMember(createMemberDto));
    }

}