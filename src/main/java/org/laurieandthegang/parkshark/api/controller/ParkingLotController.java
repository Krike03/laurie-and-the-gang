package org.laurieandthegang.parkshark.api.controller;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.ParkingLotDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.RestrictedParkingLotDto;
import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.laurieandthegang.parkshark.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADD_PARKING_LOT')")
    public ParkingLotDto registerParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        LOGGER.info("Method call: registered new ParkingLot.");
        return parkingLotService.addParkingLot(createParkingLotDto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<RestrictedParkingLotDto> getAllParkingLots(){
        LOGGER.info("Method call: get all parking lots");
        return parkingLotService.getAllParkingLots();
    }
}
