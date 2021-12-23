package org.laurieandthegang.parkshark.api.mapper.people;

import org.laurieandthegang.parkshark.api.dto.people.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.people.MemberDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.domain.people.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    private final NameMapper nameMapper;
    private final AddressMapper addressMapper;
    private final LicensePlateMapper licensePlateMapper;

    @Autowired
    public MemberMapper(NameMapper nameMapper, AddressMapper addressMapper, LicensePlateMapper licensePlateMapper) {
        this.nameMapper = nameMapper;
        this.addressMapper = addressMapper;
        this.licensePlateMapper = licensePlateMapper;
    }

    public MemberDto mapper(Member member) {
        return new MemberDto(member.getId(),
                nameMapper.mapper(member.getName()),
                addressMapper.mapper(member.getAddress()),
                member.getPhoneNumber(),
                member.getEmail(),
                licensePlateMapper.mapper(member.getLicensePlate()),
                member.getRegistrationDate());
    }

    public Member mapper(CreateMemberDto createMemberDto) {
        return new Member(
                nameMapper.mapper(createMemberDto.name()),
                addressMapper.mapper(createMemberDto.address()),
                createMemberDto.phoneNumber(),
                createMemberDto.email(),
                licensePlateMapper.mapper(createMemberDto.licensePlate()));
    }
}
