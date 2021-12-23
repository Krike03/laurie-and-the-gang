package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ParkingLotMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.laurieandthegang.parkshark.exception.RequiredFieldIsNullException;
import org.laurieandthegang.parkshark.repository.ParkingLotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.laurieandthegang.parkshark.service.Validator.VALIDATOR;

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
        VALIDATOR.validateRequiredFieldsNotNull(createParkingLotDto);

        ParkingLot parkingLot = parkingLotMapper.mapper(createParkingLotDto);
        parkingLotRepository.addParkingLot(parkingLot);
        return parkingLotMapper.mapper(parkingLot);
    }

}
