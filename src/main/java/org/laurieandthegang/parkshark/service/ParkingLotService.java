package org.laurieandthegang.parkshark.service;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.RestrictedParkingLotDto;
import org.laurieandthegang.parkshark.api.mapper.parkinglot.ParkingLotMapper;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.laurieandthegang.parkshark.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;
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

    public List<RestrictedParkingLotDto> getAllParkingLots() {
        return parkingLotRepository.getAllParkingLots().stream()
                .map(parkingLotMapper::mapEntityToTheRestrictedDto)
                .collect(Collectors.toList());
    }

}
