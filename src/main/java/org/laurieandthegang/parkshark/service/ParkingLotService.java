package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.ParkingLotMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.ParkingLotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class); // <- delete if never used in the future
    private final ParkingLotMapper parkingLotMapper;

    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
    }

    public ParkingLotDto addParkingLot(CreateParkingLotDto createParkingLotDto) {
        validateRequiredFieldsNotNull(createParkingLotDto);

        ParkingLot parkingLot = parkingLotMapper.mapper(createParkingLotDto);


        parkingLotRepository.addParkingLot(parkingLot);


        return parkingLotMapper.mapper(parkingLot);
    }

    public void validateRequiredFieldsNotNull(CreateParkingLotDto createParkingLotDto) {
        if (createParkingLotDto.name() == null) {
            throw new RequiredFieldIsNullException("NAME");
        } else if (createParkingLotDto.address().getStreetNumber() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NUMBER");
        } else if (createParkingLotDto.address().getStreetName() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - STREET NAME");
        } else if (createParkingLotDto.address().getPostalCode().getCityLabel() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - CITY LABEL");
        } else if (createParkingLotDto.address().getPostalCode().getNumeralCode() == null) {
            throw new RequiredFieldIsNullException("ADDRESS - POSTAL CODE - NUMERAL");
        } else if (createParkingLotDto.capacity() == 0) {
            throw new RequiredFieldIsNullException("CAPACITY");
        } else if (createParkingLotDto.category() == null) {
            throw new RequiredFieldIsNullException("CATEGORY");
        } else if (createParkingLotDto.contactPerson().getEmail() == null) {
            throw new RequiredFieldIsNullException("CONTACT - EMAIL");
        } else if (createParkingLotDto.contactPerson().getMobilePhoneNumber() == null
                && createParkingLotDto.contactPerson().getTelephoneNumber() == null) {
            throw new RequiredFieldIsNullException("CONTACT - MOBILE PHONE AND TELEPHONE");
        } else if (createParkingLotDto.pricePerHour() == 0) {
            throw new RequiredFieldIsNullException("PRICE PER HOUR");
        }
    }
}
