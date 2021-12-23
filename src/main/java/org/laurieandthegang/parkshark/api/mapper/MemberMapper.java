package org.laurieandthegang.parkshark.api.mapper;

import org.laurieandthegang.parkshark.api.dto.people.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.people.MemberDto;
import org.laurieandthegang.parkshark.domain.people.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberMapper() {
    }

    public MemberDto mapper(Member member) {
        return new MemberDto(member.getId(), member.getName(), member.getAddress(), member.getPhoneNumber(), member.getEmail(), member.getLicensePlate(), member.getRegistrationDate());
    }

    public Member mapper(CreateMemberDto createMemberDto) {
        return new Member(createMemberDto.name(),
                createMemberDto.address()
                , createMemberDto.phoneNumber(),
                createMemberDto.email(),
                createMemberDto.licensePlate());

    }
}
