package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.people.CreateMemberDto;
import org.laurieandthegang.parkshark.api.mapper.address.AddressMapper;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ContactPersonMapper;
import org.laurieandthegang.parkshark.api.mapper.people.LicensePlateMapper;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.springframework.beans.factory.annotation.Autowired;

public enum Validator {
    VALIDATOR;

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private LicensePlateMapper licensePlateMapper;
    @Autowired
    private ContactPersonMapper contactPersonMapper;


    public void validateRequiredFieldsNotNull(CreateParkingLotDto createParkingLotDto) {
        if (createParkingLotDto.name() == null) {
            throw new RequiredFieldIsNullException("NAME");
        } else if (addressMapper.mapper(createParkingLotDto.address()).getStreetNumber() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NUMBER");
        } else if (addressMapper.mapper(createParkingLotDto.address()).getStreetName() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NAME");
        } else if (addressMapper.mapper(createParkingLotDto.address()).getPostalCode().getCityLabel() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - CITY LABEL");
        } else if (addressMapper.mapper(createParkingLotDto.address()).getPostalCode().getNumeralCode() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - NUMERAL");
        } else if (createParkingLotDto.capacity() == 0) {
            throw new RequiredFieldIsNullException("CAPACITY");
        } else if (createParkingLotDto.category() == null) {
            throw new RequiredFieldIsNullException("CATEGORY");
        } else if (contactPersonMapper.mapper(createParkingLotDto.contactPerson()).getEmail() == null) {
            throw new RequiredFieldIsNullException("CONTACT - EMAIL");
        } else if (contactPersonMapper.mapper(createParkingLotDto.contactPerson()).getMobilePhoneNumber() == null
                && contactPersonMapper.mapper(createParkingLotDto.contactPerson()).getTelephoneNumber() == null) {
            throw new RequiredFieldIsNullException("CONTACT - MOBILE PHONE AND TELEPHONE");
        } else if (createParkingLotDto.pricePerHour() == 0) {
            throw new RequiredFieldIsNullException("PRICE PER HOUR");
        }
    }


    public void validateRequiredFieldsNotNull(CreateMemberDto createMemberDto) {
        if (createMemberDto.name() == null) {
            throw new RequiredFieldIsNullException("NAME");
        } else if (addressMapper.mapper(createMemberDto.address()).getPostalCode().getNumeralCode() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - NUMERAL");
        } else if (addressMapper.mapper(createMemberDto.address()).getPostalCode().getCityLabel() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - CITY LABEL");
        } else if (addressMapper.mapper(createMemberDto.address()).getStreetName() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NAME");
        } else if (addressMapper.mapper(createMemberDto.address()).getStreetNumber() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NUMBER");
        } else if (licensePlateMapper.mapper(createMemberDto.licensePlate()).getLicenseNumber() == null) {
            throw new RequiredFieldIsNullException("LICENSE PLATE - NUMBER");
        } else if (licensePlateMapper.mapper(createMemberDto.licensePlate()).getCountryLabel() == null) {
            throw new RequiredFieldIsNullException("LICENSE PLATE - COUNTRY LABEL");
        } else if (createMemberDto.email() == null) {
            throw new RequiredFieldIsNullException("EMAIL");
        } else if (createMemberDto.phoneNumber() == null) {
            throw new RequiredFieldIsNullException("PHONE NUMBER");
        }
    }
}
