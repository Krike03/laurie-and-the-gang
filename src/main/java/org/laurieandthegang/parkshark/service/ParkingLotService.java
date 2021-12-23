package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ParkingLotMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.laurieandthegang.parkshark.repository.ParkingLotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class); // <- delete if never used in the future
    private final ParkingLotMapper parkingLotMapper;
    private final Validator validator;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper, Validator validator) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
        this.validator = validator;
    }

    public ParkingLotDto addParkingLot(CreateParkingLotDto createParkingLotDto) {
        validator.validateRequiredFieldsNotNull(createParkingLotDto);

        ParkingLot parkingLot = parkingLotMapper.mapper(createParkingLotDto);
        parkingLotRepository.addParkingLot(parkingLot);
        return parkingLotMapper.mapper(parkingLot);
    }

}
